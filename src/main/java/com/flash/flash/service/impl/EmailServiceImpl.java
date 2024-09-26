package com.flash.flash.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.flash.flash.dao.Email;
import com.flash.flash.mapper.EmailMapper;
import com.flash.flash.service.EmailService;
import com.flash.flash.utils.BusinessException;
import com.flash.flash.utils.CodeGenerateUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 18:05
 */

@Service
public class EmailServiceImpl implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMimeMail(String to, String subject, String content) throws BusinessException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content, true);
            javaMailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
            throw new BusinessException(400, "发送邮件时发生异常！");
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try{
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content, true);

            FileSystemResource fs = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, fs);

            javaMailSender.send(message);
        }catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public void sendActiveCodeEMail(String email) throws BusinessException {

        //获取激活码
        String code = CodeGenerateUtils.creatCode(4);

        Email emailEntity = new Email(email, code, new Date());
        QueryWrapper<Email> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        if (emailMapper.exists(queryWrapper)) {
            UpdateWrapper<Email> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("email", email);
            emailMapper.update(emailEntity, updateWrapper);
        } else {
            emailMapper.insert(emailEntity);
        }

        System.out.println("激活码:"+code);
        //主题
        String subject = "智能编辑器网站注册验证码";
        //上面的激活码发送到用户注册邮箱
        String context = "您的验证码为:" + code + "，验证码10分钟内有效。";
        //发送激活邮件
        sendMimeMail(email, subject, context);
    }

    /**
     * 发送纯文本邮件
     * @param to
     * @param subject
     * @param text
     */
    @Override
    public void sendTextMailMessage(String to, String subject, String text){

        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(from);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功：" + from + "->" + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }


    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMailMessage(String to, String subject, String content){

        content="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<title>邮件</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h3>这是一封HTML邮件！</h3>\n" +
                "</body>\n" +
                "</html>";
        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(from);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容   true 代表支持html
            mimeMessageHelper.setText(content,true);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功：" + from + "->" + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }

    /**
     * 发送带附件的邮件
     * @param to      邮件收信人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param filePath 附件路径
     */
    @Override
    public void sendAttachmentMailMessage(String to, String subject, String content, String filePath){
        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(from);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容   true 代表支持html
            mimeMessageHelper.setText(content,true);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
            //添加邮件附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            mimeMessageHelper.addAttachment(fileName, file);

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功："+from+"->"+to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }

}