package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//@ResponseBody와 Controller을 합친거. data 자체를 JSON으로 보낼때 사용한다.
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    /**
     * 회원 등록
     * @param member
     * @return
     */
    // 회원 등록 API
    // RequestBody : JSON 형식의 restful API를 받는 방법이다. JSON으로 온 Body를 Member에 Mapping 해서 값을 넣어준다.
    // Valid : Entity 안에 있는것을 validation 해준다. 이때 validation을 받고싶으면 Member의
    // 변수 안에 @NotEmpty를 붙여야 한다.
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        //이제는 누가 name Entity를 userName으로 바꾸면 여기서 오류가 생성된다.
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @Data
    static class CreateMemberRequest{
        private String name;
    }

    /**
     * 멤버 수정 함수
     * 멤버 생성 함수와 request, response 함수를 같이 사용하면 안되고 따로 만들어줘야 한다.
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id,request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getName());
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    /**
     * 회원 조회
     * @return
     */
    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers(); // 엔티티를 직접 반환하면 안된다!!!
    }

    //단순하게 응답값 자체를 껍데기 클래스를 만들어준다.
    @GetMapping("/api/v2/members")
    public Result membersV2(){
        List<Member> findMembers = memberService.findMembers();
        // 이 member을 DTO로 변환
        List<MemberDTO> collect = findMembers.stream()
                .map(m -> new MemberDTO(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }
    @Data
    @AllArgsConstructor
    // t는 다양한 타입을 넣을수 있다. .any와 같음
    static class Result<T> {
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDTO{
        private String name;// 이것만 반환할수 있다!!
    }
}

