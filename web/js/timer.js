$(document).on('ready',function(){ 
	"use strict";
	
	//timer code start here
	function CountdownTimer(elm,tl,mes){
		
		 this.initialize.apply(this,arguments);
		}
		CountdownTimer.prototype={
		 initialize:function(elm,tl,mes) {
		  this.elem = document.getElementById(elm);
		  this.tl = tl;
		  this.mes = mes;
		 },countDown:function(){
			 
		  var tid='';
		  var timer='';
		  var today=new Date();
		  var day=Math.floor((this.tl-today)/(24*60*60*10000));
		  var hour=Math.floor(((this.tl-today)%(24*60*60*1000))/(60*60*1000));
		  var min=Math.floor(((this.tl-today)%(24*60*60*1000))/(60*1000))%60;
		  var sec=Math.floor(((this.tl-today)%(24*60*60*1000))/1000)%60%60;
		  var me=this;

													
		  if( ( this.tl - today ) > 0 ){
		   timer += '<ul class="list-inline">';
		   timer += '<li>'+day+'<span>Days</span></li>';
		   timer += '<li class="active">'+hour+'<span>Hours</span></li>';
		   timer += '<li>'+this.addZero(min)+'<span>Mintues</span></li>';
		   timer += '<li>'+this.addZero(sec)+'<span>Second</span></li>';
		   timer += '</ul>';
		   this.elem.innerHTML = timer;
		   tid = setTimeout( function(){me.countDown();},10 );
		  }else{
		   this.elem.innerHTML = this.mes;
		   return;
		  }
		 },addZero:function(num){ return ('0'+num).slice(-2); }
		}
		function ctime(){
		 
		 // Set countdown limit
		 var tl = new Date('2020/01/01 00:00:00');

		 // You can add time's up message here
		 var timer = new CountdownTimer('ctime',tl,'<span class="number-wrapper"><div class="line"></div><span class="number end">Time is up!</span></span>');
		 timer.countDown();
		}
		window.onload=function(){
		 ctime();
		}
	//timer code end here
	
	
			
});
