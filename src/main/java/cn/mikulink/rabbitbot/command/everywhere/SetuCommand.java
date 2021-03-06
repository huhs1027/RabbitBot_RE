package cn.mikulink.rabbitbot.command.everywhere;

import cn.mikulink.rabbitbot.command.EverywhereCommand;
import cn.mikulink.rabbitbot.constant.ConstantPixiv;
import cn.mikulink.rabbitbot.entity.CommandProperties;
import cn.mikulink.rabbitbot.entity.ReString;
import cn.mikulink.rabbitbot.service.SetuService;
import cn.mikulink.rabbitbot.sys.annotate.Command;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


/**
 * @author MikuLink
 * @date 2020/12/13 15:50
 * for the Reisen
 * <p>
 * 来点色图
 */
@Command
public class SetuCommand implements EverywhereCommand {
    private static final Logger logger = LoggerFactory.getLogger(SetuCommand.class);

    @Autowired
    private SetuService setuService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("laidiansetu", "色图", "来点色图", "来份色图", "来张色图");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        Long userId = sender.getId();
        String userNick = sender.getNick();

        //检查操作间隔
        ReString reString = setuService.setuTimeCheck(userId, userNick);
        if (!reString.isSuccess()) {
            return new PlainText(reString.getMessage());
        }

        //刷新操作间隔
        ConstantPixiv.SETU_PID_SPLIT_MAP.put(sender.getId(), System.currentTimeMillis());

        try {
            //来张色图
            return setuService.getSetu();
        } catch (FileNotFoundException fileNotFoundEx) {
            logger.warn(ConstantPixiv.PIXIV_IMAGE_DELETE + fileNotFoundEx.toString());
            return new PlainText(ConstantPixiv.PIXIV_IMAGE_DELETE);
        } catch (SocketTimeoutException stockTimeoutEx) {
            logger.warn(ConstantPixiv.PIXIV_IMAGE_TIMEOUT + stockTimeoutEx.toString(), stockTimeoutEx);
            return new PlainText(ConstantPixiv.PIXIV_IMAGE_TIMEOUT);
        } catch (Exception ex) {
            logger.error(ConstantPixiv.PIXIV_ID_GET_ERROR_GROUP_MESSAGE + ex.toString(), ex);
            return new PlainText(ConstantPixiv.PIXIV_ID_GET_ERROR_GROUP_MESSAGE);
        }
    }

}
