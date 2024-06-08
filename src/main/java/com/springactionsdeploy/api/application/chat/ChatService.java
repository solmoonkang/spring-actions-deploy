package com.springactionsdeploy.api.application.chat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springactionsdeploy.api.domain.chat.entity.ChatRoom;
import com.springactionsdeploy.api.domain.chat.repository.ChatMessageRepository;
import com.springactionsdeploy.api.domain.chat.repository.ChatRoomRepository;
import com.springactionsdeploy.api.domain.gallery.entity.Gallery;
import com.springactionsdeploy.api.domain.member.entity.Member;
import com.springactionsdeploy.api.domain.member.repository.MemberRepository;
import com.springactionsdeploy.api.infrastructure.websocket.MemberSessionRegistry;
import com.springactionsdeploy.global.error.exception.NotFoundException;
import com.springactionsdeploy.global.error.exception.UnauthorizedException;
import com.springactionsdeploy.global.error.model.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

	private final ChatRoomRepository chatRoomRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final MemberRepository memberRepository;
	private final MemberSessionRegistry memberSessionRegistry;

	public void createChatRoom(Gallery gallery) {
		final ChatRoom chatRoom = ChatRoom.createChatRoom(gallery);
		chatRoomRepository.save(chatRoom);
	}

	// @Transactional
	// public void saveAndSendChatMessage(Long chatRoomId, AuthUser authUser, ChatMessageCreateDto chatMessageCreateDto) {
	// 	final ChatRoom chatRoom = getChatRoomById(chatRoomId);
	// 	final Member member = getMemberByEmail(authUser.email());
	//
	// 	final ChatMessage chatMessage = ChatMessage.createChatMessage(chatRoom, member, chatMessageCreateDto);
	// 	chatMessageRepository.save(chatMessage);
	// }

	private ChatRoom getChatRoomById(Long chatRoomId) {
		return chatRoomRepository.findById(chatRoomId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_CHAT_ROOM_NOT_FOUND));
	}

	private Member getMemberByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new UnauthorizedException(ErrorCode.FAIL_LOGIN_REQUIRED));
	}
}
