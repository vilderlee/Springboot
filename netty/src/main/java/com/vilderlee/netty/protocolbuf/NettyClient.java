package com.vilderlee.netty.protocolbuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.io.DataInputStream;
import java.util.concurrent.CountDownLatch;

/**
 * 功能描述:
 *
 * @package com.vilderlee.netty.protocolbuf
 * @auther vilderlee
 * @date 2019/11/16 6:11 下午
 */
public class NettyClient {
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args)  {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new MyChannelInitializer());

        ChannelFuture localhost = bootstrap.connect("localhost", 8899);

        try {
            localhost.sync().channel().closeFuture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                COUNT_DOWN_LATCH.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.shutdownGracefully();
        }

    }


    private static class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline()
                    .addLast(new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()))
                    .addLast(new ProtobufEncoder())
                    .addLast(new ProtobufVarint32FrameDecoder())
                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                    .addLast(new MyClientHandler());
        }
    }


    public static class MyClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

        @Override
        protected void channelRead0(ChannelHandlerContext context, MyDataInfo.Person person) throws Exception {
            System.out.println(person);
            COUNT_DOWN_LATCH.countDown();
        }


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("VilderLee").setAge(25).setAddress("BeiJing").build();
            ctx.writeAndFlush(person);
        }
    }
}
