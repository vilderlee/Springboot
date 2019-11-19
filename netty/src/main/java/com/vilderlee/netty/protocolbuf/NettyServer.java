package com.vilderlee.netty.protocolbuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 功能描述:
 *
 * @package com.vilderlee.netty.protocolbuf
 * @auther vilderlee
 * @date 2019/11/16 5:53 下午
 */
public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker).childOption(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MyServerChildHandler());
        ChannelFuture sync = null;
        try {
            sync = bootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }


    private static class MyServerChildHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline()
                    .addLast(new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()))
                    .addLast(new ProtobufEncoder())
                    .addLast(new ProtobufVarint32FrameDecoder())
                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                    .addLast(new MyServerHandler());


        }


    }

    private static class MyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
        @Override
        protected void channelRead0(ChannelHandlerContext context, MyDataInfo.Person person) throws Exception {
            System.out.println(person);
            MyDataInfo.Person build = MyDataInfo.Person.newBuilder().setName("MrLee").setAddress("SX").setAge(1).build();
            context.writeAndFlush(build);
        }
    }

}
