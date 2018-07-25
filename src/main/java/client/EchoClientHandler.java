package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

        System.out.println("Client receive :"+byteBuf.toString(CharsetUtil.UTF_8));

    }

     @Override
      public void  exceptionCaught(ChannelHandlerContext ctx ,Throwable cause)throws Exception
     {
                      cause.printStackTrace();
                      ctx.close();

     }
}
