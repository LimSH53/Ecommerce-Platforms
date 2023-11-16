package com.naedam.admin.board.model.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.board.model.dao.BoardDao;
import com.naedam.admin.board.model.vo.Board;

import com.naedam.admin.board.model.vo.BoardComment;
import com.naedam.admin.board.model.vo.BoardFile;
import com.naedam.admin.board.model.vo.BoardRequest;
import com.naedam.admin.board.model.vo.BoardTranslate;
import com.naedam.admin.board.model.vo.Post;
import com.naedam.admin.board.model.vo.PostRequest;
import com.naedam.admin.member.model.vo.Member;
import com.naedam.admin.recruit.model.dao.RecruitDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private RecruitDao recruitDao;

	public Map<String, Object> boardProcess(BoardRequest boardRequest) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		Board board = boardRequest.getBoard();
		String mode = boardRequest.getMode();
		HttpServletRequest request = boardRequest.getRequest();

		if ("insert".equals(mode)) {
			resultMap = addBoard(board, boardRequest);
			// 게시판 수정, 권한 수정, 옵션 수정
		} else if ("update".equals(mode)) {
			resultMap = updateBoard(board, boardRequest);
			// 게시판 삭제
		} else if ("delete".equals(mode)) {
			List<String> boardNoList = Arrays.asList(request.getParameterValues("list[]"));
			resultMap = deleteChoiceBoard(boardNoList);

		}

		return resultMap;
	}

	public Map<String, Object> addBoard(Board board, BoardRequest boardRequest) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boardDao.addBoard(board);
		resultMap.put("msg", "게시판이 등록되었습니다.");
		return resultMap;
	}

	public Map<String, Object> updateBoard(Board board, BoardRequest boardRequest) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boardDao.updateBoard(board);
		resultMap.put("msg", "게시판이 수정되었습니다.");
		return resultMap;
	}

	public Map<String, Object> deleteChoiceBoard(List<String> boardNoList) throws NumberFormatException, Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (String no : boardNoList) {
			boardDao.deleteChoiceBoard(Integer.parseInt(no));
		}
		resultMap.put("msg", "게시판 정보가 삭제되었습니다.");
		return resultMap;
	}

	// 게시글 프로세스
	public Map<String, Object> postProcess(PostRequest postRequest) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Post post = postRequest.getPost();

		String mode = postRequest.getMode();
		String filePath = postRequest.getFilePath();

		// 게시글 등록
		if ("insert".equals(mode)) {
			post.setPostBoard(postRequest.getBoard());
			resultMap = addPost(postRequest, filePath);
			// 게시글 수정
		}
		if ("update".equals(mode)) {
			post.setPostBoard(postRequest.getBoard());
			resultMap = updatePost(post, postRequest, filePath);
		}
		// 게시글 선택삭제
		if ("delete".equals(mode)) {
			List<String> postArr = postRequest.getPostArr();
			resultMap = deleteChoicePost(postArr);
			// 게시글 복사
		}
		if ("copy".equals(mode)) {
			List<String> postArr = postRequest.getPostArr();
			for (String i : postArr) {
				post = boardDao.getPostData(Integer.parseInt(i));
				resultMap = copyPost(post, postRequest, filePath);
			}
			// 게시글 이전
			// 기존의 있던 데이터를 복사하여 insert 함, 기존에 있던 데이터는 delete

		}
		if ("change".equals(mode)) {
			List<String> postArr = postRequest.getPostArr();
			for (String i : postArr) {
				post = boardDao.getPostData(Integer.parseInt(i));
				resultMap = copyPost(post, postRequest, filePath);
			}
			resultMap = deleteChoicePost(postArr);
		}

		return resultMap;
	}

	public Map<String, Object> addPost(PostRequest postRequest, String filePath)
			throws ParseException, IllegalStateException, IOException, Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		BoardFile boardFile = new BoardFile();
		String secNo = postRequest.getSecNo();
		Post post = postRequest.getPost();

		Member member = boardDao.getMemberData(Integer.parseInt(secNo.toString()));
		post.setPostMember(member);
		post.setPostMemberName(member.getLastName() + member.getFirstName());

		MultipartFile[] postImage = postRequest.getPostImage();
		// post.setPostBoard(postRequest.getBoard());
		post.setLocale(postRequest.getLocale());
		boardDao.addPost(post);

		for (int i = 0; i < postImage.length; i++) {
			File file = new File(filePath + postImage[i].getOriginalFilename());
			boardFile.setFilePost(post);
			boardFile.setFileName(postImage[i].getOriginalFilename());
			post.setImgUrl(postImage[i].getOriginalFilename());
			postImage[i].transferTo(file);
			boardDao.addFile(boardFile);
		}

		resultMap.put("msg", "게시판이 등록되었습니다.");
		return resultMap;
	}

	public Map<String, Object> updatePost(Post post, PostRequest postRequest, String filePath)
			throws ParseException, IllegalStateException, IOException, Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		BoardFile boardFile = new BoardFile();
		post.setPostBoard(postRequest.getBoard());
		MultipartFile[] postImage = postRequest.getPostImage();
		post.setLocale(postRequest.getLocale());
		// 게시글 등록시 현재 시큐리티에 접속해있는 아이디(primary key)가 필요합니다.
		String secNo = postRequest.getSecNo();
		Member member = boardDao.getMemberData(Integer.parseInt(secNo.toString()));
		post.setPostMember(member);
		post.setPostMemberName(member.getLastName() + member.getFirstName());

		for (int i = 0; i < postImage.length; i++) {
			File file = new File(filePath + postImage[i].getOriginalFilename());
			boardFile.setFilePost(post);
			boardFile.setFileName(postImage[i].getOriginalFilename());
			postImage[i].transferTo(file);
			post.setImgUrl(postImage[i].getOriginalFilename());
			postImage[i].transferTo(file);
			boardDao.addFile(boardFile);
		}

		boardDao.updatePost(post);
		resultMap.put("msg", "게시판이 수정되었습니다.");
		return resultMap;
	}

	public Map<String, Object> copyPost(Post post, PostRequest postRequest, String filePath)
			throws ParseException, IllegalStateException, IOException, Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		post.getPostBoard().setBoardNo(postRequest.getBoardNo());
		boardDao.addPost(post);
		BoardFile boardFile = new BoardFile();
		boardFile.setFilePost(post);
		boardFile.setFileNo(post.getPostOriginNo());
		boardFile.setFileName(boardDao.getfileName(post.getPostOriginNo()));
		if (boardFile.getFileName() != null) {
			boardDao.addFile(boardFile);
		}

		resultMap.put("msg", "게시판이 등록되었습니다.");
		return resultMap;
	}

	public Map<String, Object> deleteChoicePost(List<String> postArr) throws NumberFormatException, Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (String no : postArr) {
			boardDao.deleteChoicePost(Integer.parseInt(no));
		}
		resultMap.put("msg", "게시판 정보가 삭제되었습니다.");
		return resultMap;
	}

	@Override
	public int addComment(BoardComment boardComment) throws Exception {
		return boardDao.addComment(boardComment);
	}

	@Override
	public int addTranslate(BoardTranslate boardTranslate) throws Exception {
		return boardDao.addTranslate(boardTranslate);
	}

	@Override
	public int addFile(BoardFile boardFile) throws Exception {
		return boardDao.addFile(boardFile);
	}

	@Override
	public Map<String, Object> getBoardList(Map<String, Object> map) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", boardDao.getBoardList(map));
		resultMap.put("totalCount", boardDao.getTotalCount(map));
		List<Board> boardList = (List<Board>) map.get("board");
		List postCount = new ArrayList<>();
		for (int i = 0; i < boardList.size(); i++) {
			int count = boardDao.getTotalCount3(boardList.get(i).getBoardNo());
			postCount.add(count);
		}
		resultMap.put("postCount", postCount);
		return resultMap;
	}

	@Override
	public Map<String, Object> getPostList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", boardDao.getPostList(map));
		resultMap.put("totalCount", boardDao.getTotalCount2(map));
		// 채용
		resultMap.put("recruitList", recruitDao.getRecruitList(null, 0, 5, (String) map.get("locale")));
		return resultMap;
	}

	@Override
	public List<Post> getUserPostList(Map<String, Object> map) throws Exception {
		return boardDao.getUserPostList(map);
	}

	@Override
	public List<Post> getMainPostList(Map<String, Object> map) throws Exception {
		return boardDao.getMainPostList(map);
	}

	@Override
	public int getUserGetTotalCount(Map<String, Object> map) throws Exception {
		return boardDao.getTotalCount2(map);
	}

	@Override
	public List<BoardComment> getCommentList(int postNo) throws Exception {
		return boardDao.getCommentList(postNo);
	}

	@Override
	public List<Board> getBoardTitle(Map<String, Object> map) throws Exception {
		return boardDao.getBoardTitle(map);
	}

	@Override
	public Board getBoardData(int boardNo) throws Exception {
		return boardDao.getBoardData(boardNo);
	}

	@Override
	public Post getPostData(int postNo) throws Exception {
		return boardDao.getPostData(postNo);
	}

	@Override
	public Board getBoardAllData(int boardNo) throws Exception {
		return boardDao.getBoardAllData(boardNo);
	}

	@Override
	public Member getMemberData(int memberNo) throws Exception {
		return boardDao.getMemberData(memberNo);
	}

	@Override
	public List<BoardFile> getPostFile(int postNo) throws Exception {
		return boardDao.getPostFile(postNo);
	}

	@Override
	public BoardFile getFileData(int fileNo) throws Exception {
		return boardDao.getFileData(fileNo);
	}

	@Override
	public void deleteFile(int fileNo) throws Exception {
		boardDao.deleteFile(fileNo);
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		boardDao.deleteComment(commentNo);
	}

	@Override
	public int postViewCount(Post post) throws Exception {
		return boardDao.postViewCount(post);
	}

	@Override
	public int postFileCount(Post post) throws Exception {
		return boardDao.postFileCount(post);
	}

	@Override
	public int updatePostReply(Post post) throws Exception {
		return boardDao.updatePostReply(post);
	}

	@Override
	public int updateThombnail(Post post) throws Exception {
		return boardDao.updateThombnail(post);
	}

	@Override
	public int getTotalCount3(int boardNo) throws Exception {
		return boardDao.getTotalCount3(boardNo);
	}

	// down순서변경
	@Override
	public void updateDownAsc(Map<String, Object> map) throws Exception {
		boardDao.updateDownAsc(map);
	}

	// up순서변경
	@Override
	public void updateUpAsc(Map<String, Object> map) throws Exception {
		boardDao.updateUpAsc(map);
	}

	@Override
	public Map<String, Object> getNoticeDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Post post = boardDao.getPostData((Integer) map.get("postNo"));
		resultMap.put("post", post);
		resultMap.put("postLocale", post.getLocale());
		resultMap.put("boardFile", boardDao.getPostFile(post.getPostNo()));
		resultMap.put("board", boardDao.getBoardData(post.getPostBoard().getBoardNo()));
		resultMap.put("postPrev", boardDao.getPrevPost(post));
		resultMap.put("postNext", boardDao.getNextPost(post));
		return resultMap;
	}

	// dashboard
	@Override
	public List<String> getBoardList(String locale) {

		return boardDao.getBoardList(locale);
	}

}
