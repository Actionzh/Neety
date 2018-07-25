package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
    {
        //将客户端传入的消息转化为netty的bytebuf类型
        ByteBuf in = (ByteBuf)msg;

        System.out.println("server received:"+in.toString(CharsetUtil.UTF_8));

        //将接收到的消息写给发送者，而不冲刷出站消息
        ctx.write(in);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
    {
        //将未处理的消息冲刷到远程节点，并且关闭channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 异常处理
     */

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)
    {
        //打印异常栈跟踪
        cause.printStackTrace();

        //关闭该channel
        ctx.close();
    }

}
