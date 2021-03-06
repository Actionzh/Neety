package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;

public class EchoClient {
    private final static String HOST="localhost";
    private final static int PORT=8080;

    public static void start(){
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
//                .remoteAddress(new InetSocketAddress(HOST,PORT))
                .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                    protected void  initChannel(SocketChannel socketChannel)
                {
                   socketChannel.pipeline().addLast(new EchoClientHandler());
                }
                });

        try {
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            ChannelFuture f=bootstrap.connect(HOST,PORT).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String [] args)
    {
        start();
    }


}
