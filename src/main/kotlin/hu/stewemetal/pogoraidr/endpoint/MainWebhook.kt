package hu.stewemetal.pogoraidr.endpoint

import com.github.messenger4j.Messenger
import com.github.messenger4j.Messenger.*
import com.github.messenger4j.exception.MessengerApiException
import com.github.messenger4j.exception.MessengerIOException
import com.github.messenger4j.send.MessagePayload
import com.github.messenger4j.send.message.TextMessage
import com.github.messenger4j.webhook.Event
import com.github.messenger4j.webhook.event.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.Optional.of

@RestController
@RequestMapping("/webhook")
class MainWebhook(val messenger: Messenger) {
    private val RESOURCE_URL = "https://raw.githubusercontent.com/fbsamples/messenger-platform-samples/master/node/public"

    private val logger = LoggerFactory.getLogger(MainWebhook::class.java)

    @RequestMapping(method = [RequestMethod.GET])
    fun verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) mode: String,
                      @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) verifyToken: String,
                      @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) challenge: String): ResponseEntity<String> {

        try {
            messenger.verifyWebhook(mode, verifyToken)
            return ResponseEntity.ok(challenge)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message)
        }

    }

    @RequestMapping(method = [RequestMethod.POST])
    fun handleCallback(@RequestBody requestPayload: String?,
                       @RequestHeader(SIGNATURE_HEADER_NAME) signature: String?) {
        try {
            logger.info("RequestBody: {} ; signature: {}", requestPayload, signature)
            if (requestPayload != null) {
                val sign = if (signature == null) Optional.empty<String?>() else of(signature)
                messenger.onReceiveEvents(requestPayload, sign, { event -> handleMessengerCallbackEvent(event) })
            }
        }catch (e:Exception){
            logger.info("Error in callback handling: {}", e.message)
        }
    }

    fun handleMessengerCallbackEvent(event: Event) {
        when {
            event.isAccountLinkingEvent -> handleAccountLinkingEvent(event.asAccountLinkingEvent())
            event.isAttachmentMessageEvent -> handleAttachmentMessageEvent(event.asAttachmentMessageEvent())
            event.isMessageDeliveredEvent -> handleMessageDeliveredEvent(event.asMessageDeliveredEvent())
            event.isMessageEchoEvent -> handleMessageEchoEvent(event.asMessageEchoEvent())
            event.isMessageReadEvent -> handleMessageReadEvent(event.asMessageReadEvent())
            event.isOptInEvent -> handleOptInEvent(event.asOptInEvent())
            event.isPostbackEvent -> handlePostBackEvent(event.asPostbackEvent())
            event.isQuickReplyMessageEvent -> handleQuickReplyMessageEvent(event.asQuickReplyMessageEvent())
            event.isReferralEvent -> handleReferralEvent(event.asReferralEvent())
            event.isTextMessageEvent -> handleTextMessageEvent(event.asTextMessageEvent())
        }

    }

    private fun handleTextMessageEvent(event: TextMessageEvent) {

        logger.debug("Received TextMessageEvent: {}", event)

        val senderId = event.senderId()
        val timestamp = event.timestamp()

        val messageText = event.text()
        val messageId = event.messageId()

        logger.info("Received message '{}' with text '{}' from user '{}' at '{}'", messageId, messageText, senderId, timestamp)

        try {
            val textMessage = TextMessage.create(messageText)
            val payload = MessagePayload.create(senderId, textMessage)
            messenger.send(payload)
        } catch (mae: MessengerApiException) {
            handleSendException(mae)
        } catch (mie: MessengerIOException) {
            handleSendException(mie)
        }
    }

    private fun handleReferralEvent(event: ReferralEvent) {
        logger.info("Referral event arrived: {}",event)
    }

    private fun handleQuickReplyMessageEvent(event: QuickReplyMessageEvent) {
        logger.info("QuickReplyMessage event arrived: {}",event)
    }

    private fun handlePostBackEvent(event: PostbackEvent) {
        logger.info("PostBack event arrived: {}",event)
    }

    private fun handleOptInEvent(event: OptInEvent) {
        logger.info("OptIn event arrived: {}",event)
    }

    private fun handleMessageReadEvent(event: MessageReadEvent) {
        logger.info("MessageRead event arrived: {}",event)
    }

    private fun handleMessageEchoEvent(event: MessageEchoEvent) {
        logger.info("MessageEcho event arrived: {}",event)
    }

    private fun handleMessageDeliveredEvent(event: MessageDeliveredEvent) {
        logger.info("MessageDelivered event arrived: {}",event)
    }

    private fun handleAttachmentMessageEvent(event: AttachmentMessageEvent) {
        logger.info("AttachmentMessage event arrived: {}",event)
    }

    private fun handleAccountLinkingEvent(event: AccountLinkingEvent) {
        logger.info("AccountLinking event arrived: {}",event)
    }

    private fun handleSendException(e: MessengerIOException) {
        logger.error("Message could not be sent. An unexpected error occurred.", e)
    }

    private fun handleSendException(e: MessengerApiException) {
        logger.error("Message could not be sent. An unexpected error occurred.", e)
    }
}
