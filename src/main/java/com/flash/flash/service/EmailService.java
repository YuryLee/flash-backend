package com.flash.flash.service;

import com.flash.flash.utils.BusinessException;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/7/11 下午6:04
 */
public interface EmailService {
    /**
     *  发送多媒体类型邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendMimeMail(String to, String subject, String content) throws BusinessException;

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

    void sendActiveCodeEMail(String mail) throws BusinessException;

    void sendTextMailMessage(String to, String subject, String text);

    void sendHtmlMailMessage(String to, String subject, String content);

    void sendAttachmentMailMessage(String to, String subject, String content, String filePath);
}
