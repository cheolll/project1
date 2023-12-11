/* Guide.js */
function guideInsert(){
   if(document.guideInsertForm.guideTitle.value == ""){
      alert("제목을 입력하세요~");
      document.guideInsertForm.guideTitle.focus();
   }
   else if(document.guideInsertForm.guideContents.value == ""){
      alert("내용을 입력하세요~");
      document.guideInsertForm.guideContents.focus();
   }
   else {
      document.guideInsertForm.submit();
   }
}