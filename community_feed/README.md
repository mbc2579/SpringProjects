### 1. 유저 생성 기능 (유저 메인 서비스 FLOW)
* 유저는 id를 통해 구분이 되어있어야 함
* 이름, 프로필 이미지를 입력 받으면 저장되어야 함
  + 단, 이름은 빈 값이 입력되면 안됨
***
 ### 2. 팔로우 기능
* 사용자는 다른 이용자를 팔로우 할 수 있음
* 팔로우 취소 할 수 있음
* 자기 자신을 팔로우 할 수 없음
1. 팔로우 유저, 팔로우 당하는 유저 입력을 받고 불러오기
2. 팔로우 여부 확인하기
   - 팔로우 상태라면, 에러를 반환하기
3. 자기 자신이라면 에러를 반환하기
4. 팔로우 유저 카운트 변경
5. 팔로잉 유저 카운트 변경
6. 팔로우 여부 저장 및 유저 변경 사항 저장
***
 ### 3. 프로필 노출
* 사용자 프로필을 누르면 다음과 같은 정보들이 노출 됨
  + 이름, 유저 프로필, 팔로잉 숫자, 팔로워 숫자
  + 팔로잉 팔로우 유저 리스트
    * 이름, 프로필
    * 단, 팔로잉,팔로우 숫자를 클릭 했을 때에 각 유저 리스트가 노출됨
***

 ### 4. 피드 서비스 메인 FLOW
1. 게시물 작성
   - 사용자가 텍스트를 입력하고
   - 사용자가 공개 대상 (예: 모두 공개, 팔로워 전용)을 선택합니다.
   - 사용자가 게시물을 제출합니다.
     - 단, 게시물의 글자수는 5글자 이상 500자 이하여야 합니다. <br/>
     
1-1. 게시글의 정보를 입력받는다. <br/>
1-2. 유저 유효성 확인 - 존재하는 유저인지 <br/>
1-3. 게시글의 유효성 확인 (5자 이상 500자 이하) <br/>
1-4. db 게시글 저장
***

2. 댓글 작성
   - 사용자가 텍스트를 입력하고
   - 사용자가 댓글을 제출합니다.
     - 단, 댓글의 글자수는 100자 이하여야 합니다.
   - 사용자가 게시물을 제출합니다.
     - 단, 게시물의 글자수는 5글자 이상 500자 이하여야 합니다. <br/>

2-1. 댓글의 정보를 입력받기 <br/>
2-2. 유저의 유효성 확인 - 존재하는지 <br/>
2-3. 게시글 유효성 확인 - 존재하는지 <br/>
2-4. 댓글 유효성 확인 <br/>
2-5. db 댓글 저장
***

3. 게시물 상호작용
   - 사용자는 '좋아요' 버튼을 눌러 게시물을 좋아할 수 있습니다.
     - 본인 게시글은 본인이 좋아요를 누를 수 없습니다.
   - 사용자는 댓글 섹션에 메시지를 입력하여 게시물에 댓글을 달 수 있습니다.
   - 좋아요 개수를 누르면 좋아요를 누른 인원들을 볼 수 있습니다.
   - 댓글 개수를 누르면 댓글 리스트가 보일 수 있습니다.
   - 글 수정 버튼을 누르면 글을 수정할 수 있습니다.
     - 본인이 작성한 글이 아니면 수정 할 수 없습니다.
     - 수정이 된 여부와 수정된 시간을 같이 저장해야 합니다.
   - 댓글 수정 버튼을 누르면 댓글을 수정 할 수 있습니다.
     - 본인이 작성한 댓글이 아니면 수정 할 수 없습니다.
     - 수정이 된 여부와 수정된 시간을 같이 저장해야 합니다. <br>

3-1. 유저번호와 좋아요를 누를 게시글의 번호를 입력 받습니다. <br>
3-2. 게시글과 유저의 좋아요 존재 여부를 확인합니다. <br>
3-3. 유저 본인 게시글인지 확인합니다. <br>
3-4. 게시글의 좋아요 수 증가 <br>
3-5. db 저장 및 결과를 반환
