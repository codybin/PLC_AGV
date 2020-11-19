/**
 * Copyright (c) Fraunhofer IML
 */
package com.xintai.plc.comadpater;

import com.xintai.vehicle.comadpter.*;
import com.google.inject.assistedinject.Assisted;
import com.xintai.kecong.message.KeCongCommandResponse;
import com.xintai.kecong.message.KeCongRequestMessage;
import java.util.LinkedList;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Queue;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Keeps {@link Request}s in a queue and matches them with incoming {@link Response}s.
 *��¼����һ�������У�ƥ�����Ǻ͵����Ļ�Ӧ
 * @author Stefan Walter (Fraunhofer IML)
 */
public class RequestResponseMatcher {

  /**
   * This class's logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RequestResponseMatcher.class);
  /**
   * The actual queue of requests.
   * ��ʵ���������
   */
  private final Queue<KeCongRequestMessage> requests = new LinkedList<>();
  /**
   * Sends the queued {@link Request}s.
   * ���Ͷ���
   */
  private final TelegramSender telegramSender;

  /**
   * Creates a new instance.
   * �����µ�ʵ��
   *
   * @param telegramSender Sends the queued {@link Request}s.
   */
  @Inject
  public RequestResponseMatcher(@Assisted TelegramSender telegramSender) {
    this.telegramSender = requireNonNull(telegramSender, "telegramSender");
  }

  public void enqueueRequest(@Nonnull KeCongRequestMessage request) {
    requireNonNull(request, "request");
    boolean emptyQueueBeforeEnqueue = requests.isEmpty();

    LOG.debug("Enqueuing request: {}", request);
    requests.add(request);
    System.out.println("com.xintai.vehicle.comadpter.RequestResponseMatcher.enqueueRequest()");
    if (emptyQueueBeforeEnqueue) {
      checkForSendingNextRequest();
   // requests.remove();//Added by codybin
      
    }
  }

  /**
   * Checks if a telegram is enqueued and sends it.
   * ����Ƿ�һ���������в��ҷ�����
   */
  public void checkForSendingNextRequest() {
    LOG.debug("Check for sending next request.");
    if (peekCurrentRequest().isPresent()) {
      telegramSender.sendTelegram(peekCurrentRequest().get());
      System.out.println("com.xintai.vehicle.comadpter.RequestResponseMatcher.checkForSendingNextRequest()");
    }
    else {
      LOG.debug("No requests to be sent.");
    }
  }

  /**
   * Returns the next request in the queue or an {@link Optional#EMPTY} if none is present.
   *
   * @return The next request in the queue or an {@link Optional#EMPTY} if none is present
   */
  public Optional<KeCongRequestMessage> peekCurrentRequest() {
    return Optional.ofNullable(requests.peek());
  }

  /**
   * Returns <code>true</code> if the response matches to the first request in the queue.
   * If it matches, the request will be removed.
   *����Ƿ��Ƿ��ͳ�ȥ�Ĵ���
   * @param response The response to match
   * @return <code>true</code> if the response matches to the first request in the queue.
   */
  public boolean tryMatchWithCurrentRequest(@Nonnull KeCongCommandResponse response) {
    requireNonNull(response, "response");
    KeCongRequestMessage currentRequest = requests.peek();
    if (currentRequest != null && response.isResponseTo(currentRequest)) {
      requests.remove();
      return true;
    }

    if (currentRequest != null) {
      LOG.info("No request matching response with counter {}. Latest request counter is {}.",
               response.getcqs(), currentRequest.getcqs());
    }
    else {
      LOG.info("Received response with counter {}, but no request is waiting for a response.",
               response.getcqs());
    }

    return false;
  }

  /**
   * Clears all requests stored in the queue.
   * ����ڶ��������е�����
   */
  public void clear() {
    requests.clear();
  }
}
