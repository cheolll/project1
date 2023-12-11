function checkInfoUpdate() {
   /* 글자수만 제한 2~20 */
   const regNickName = new RegExp(/^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/);

   /* 휴대폰 번호 형식*/
   const regPhoneNumber = new RegExp(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/);

   const regEmail = new RegExp(/^[a-z0-9\.\-_]/);
   const regDomain = new RegExp(/([a-z0-9\-]+\.)+[a-z]{2,6}$/);

   if (document.infoUpdateForm.nickName.value == "") {
      alert("닉네임을 입력하세요~");
      document.infoUpdateForm.name.focus();
   } else if (regNickName.test(document.infoUpdateForm.nickName.value) != true) {
      alert("닉네임은 2 ~ 20글자")
      document.infoUpdateForm.nickName.focus();
   }
   else if (document.infoUpdateForm.phoneNumber.value == "") {
      alert("전화번호를 입력하세요~");
      document.infoUpdateForm.phoneNumber.focus();
   } else if (regPhoneNumber.test(document.infoUpdateForm.phoneNumber.value) != true) {
      alert("정확한 번호를 입력해 주세요")
      document.infoUpdateForm.phoneNumber.focus();
   }
   else if (document.infoUpdateForm.birth.value == "") {
      alert("생년월일을 입력하세요~");
      document.infoUpdateForm.birth.focus();
   }
   else if (document.infoUpdateForm.email.value == "") {
      alert("이메일을 입력하세요~");
      document.infoUpdateForm.email.focus();
   } else if (regEmail.test(document.infoUpdateForm.email.value) != true) {
      alert("이메일을 잘못입력했습니다.")
      document.infoUpdateForm.email.focus();
   }
   else if (document.infoUpdateForm.domain.value == "") {
      alert("도메인을 입력하세요~");
      document.infoUpdateForm.domain.focus();
   } else if (regDomain.test(document.infoUpdateForm.domain.value) != true) {
      alert("도메인을 잘 입력하세요")
      document.infoUpdateForm.domain.focus();
   }
   else if (document.infoUpdateForm.postcode.value == "") {
      alert("우편번호를 입력하세요~");
      document.infoUpdateForm.detailAddress.focus();
   } else if (document.infoUpdateForm.address.value == "") {
      alert("주소를 입력하세요~");
      document.infoUpdateForm.detailAddress.focus();
   } else if (document.infoUpdateForm.detailAddress.value == "") {
      alert("상세 주소를 입력하세요~");
      document.infoUpdateForm.detailAddress.focus();
   }
   else {
      document.infoUpdateForm.submit();
   }
}

function checkInfoUpdate() {

   /* pw : 최소 8자리, 숫자,문자,특수문자 최소 1개 */
   const regPw = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/);

   if (document.infoPwdUpdateForm.password.value == "") {
      alert("비밀번호를 입력하세요~");
      document.infoPwdUpdateForm.password.focus();
   } else if (regPw.test(document.infoPwdUpdateForm.password.value) != true) {
      alert("비밀번호는 최소 8자리, 숫자,문자,특수문자를 최소 1개씩은 포함해야 합니다.")
      document.infoPwdUpdateForm.password.focus();
   }
   else if (document.infoPwdUpdateForm.password.value != document.infoPwdUpdateForm.repassword.value) {
      alert("비밀번호가 틀려요~");
      document.infoPwdUpdateForm.repassword.focus();
   } else {
      document.infoPwdUpdateForm.submit();
   }
}