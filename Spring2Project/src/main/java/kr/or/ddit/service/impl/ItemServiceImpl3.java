package kr.or.ddit.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.IItemMapper3;
import kr.or.ddit.service.IItemService3;
import kr.or.ddit.vo.Item3;

@Service
public class ItemServiceImpl3 implements IItemService3 {

	@Inject
	private IItemMapper3 mapper;

	@Override
	public void register(Item3 item) {
		// 일반적인 데이터를 insert
		mapper.create(item);

		// 일반적인 데이터를 담고 있는 item3 데이터 안에 파일 데이터가 들어있는데
		// 넘겨받은 파일 데이터를 파일 테이블에 넣는다.

		String[] files = item.getFiles();
		if (files == null) {
			return;
		}

		for (String fileName : files) {
			mapper.addAttach(fileName);
		}
	}

	@Override
	public List<Item3> list() {
		return mapper.list();
	}

	@Override
	public Item3 read(int itemId) {
		return mapper.read(itemId);
	}

	@Override
	public List<String> getAttach(int itemId) {
		return mapper.getAttach(itemId);
	}

	@Override
	public void modify(Item3 item) {
		// 일반적인 데이터를 update
		mapper.update(item);

		// itemId에 해당하는 모든 파일 데이터 삭제
		int itemId = item.getItemId();
		mapper.deleteAttach(itemId);
		
		// 넘겨받은 fileName 정보 즉, 넘겨받은 수정해야할 파일 데이터를 추가
		String[] files = item.getFiles();
		
		if(files == null) {
			return;
		}
		for(String fileName : files) {
			mapper.replaceAttach(fileName, itemId);
		}
	}

	@Override
	public void remove(int itemId) {
		mapper.deleteAttach(itemId);
		mapper.delete(itemId);
	}

}
