/**
 * 
 */
	$(document).ready(function(){
   CODE.initCode();
   //验证输入
   function checkInput(){
    if($('#userName').val()==''){
     alert('用户名不能为空！');
     return false;
    }
    if($('#password').val()==''){
     alert('密码不能为空！');
     return false;
    }
    if($('#code').val()==''){
     alert('验证码不能为空！');
     return false;
    }
    return true;
   }

   //点击按钮
   $('#btn').click(function(){
    if(checkInput()==true){    	
    $.ajax({
      url:'verify.action',
      data: {
          code: $("#code").val(),       
      },
      cache:false,
      type:'POST',
      success:function(data){
       if(data==0){
        alert('验证码错误！');        
        CODE.initCode();//改变验证码
       }else if(data==1){       
        $('#login_request')[0].submit();
       }
      },
      error:function(e){
       alert('出错了！');
      }
     });
     }
   });
  });
  var CODE={
    //初始化化验证码
    initCode:function(){
     $("#code_img").attr("src","verification.do?rmd="+new Date().getTime())//如果需要点击图片改变图片的内容，则必须添加时间搓
     .click(function(){
      $(this).attr("src","verification.do?rmd="+new Date().getTime());
     }); 
    }};		