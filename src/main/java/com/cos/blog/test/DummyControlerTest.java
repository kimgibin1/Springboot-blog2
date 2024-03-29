package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;



@RestController
public class DummyControlerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id :"+id;
	}
	
	
	@Transactional // 더티 체킹(변경감지 DB수정) <함수 종료시에 자동 commit 됨>
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {  //json 데이터를 요청 -> java objct로 변환해서 받음
       System.out.println("id : "+id);
       System.out.println("password : "+requestUser.getPassword());
       System.out.println("email : "+requestUser.getEmail());
       
       User user = userRepository.findById(id).orElseThrow(()->{
    	   return new IllegalArgumentException("수정에 실패하였습니다.");
       });
       user.setPassword(requestUser.getPassword());
       user.setEmail(requestUser.getEmail());

      // userRepository.save(user);  //  save는 insert할때 사용. 위 람다식과 같이 안쓰게되면 나머지 db value가 null 되버림
       return user;
    
    }
	
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll(); // findAll = 데이터베이스에 있는 모든 데이터를 가져와야 하는 경우에 자주 사용
	}

	
	// {id} 주소로 파마레터를 전달받을수있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() { // user 테이블에 없는 id 값 입력 시 리턴
			// TODO Auto-generated method stub
			return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
		}
		});
		// user 객체 = 자바 오브젝트
		// 리턴 = 웹브라우저가 이해할수있는 데이터로 변환 필요 -> json
		// 스프링분트 = MesaageConverter  자바 오브젝트 리턴 시  MesaageConverter가 작동하여 jacson 라이브러리 호출
		// user 오브젝트를 json으로 변환하여 웹브라우저에 전송함
			return user;
	}
	
	// http://localhost:8000/blog/dummy/join
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : "+user.getId());
		System.out.println("usernam : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
