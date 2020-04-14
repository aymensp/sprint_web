function loadscroler(){
		
}
$(document).on('ready',function(){ 
	"use strict";
	loadscroler();
	
	
	/*slideshow script code start here*/
	$('.slideshow').owlCarousel({
		items: 1,
		autoPlay: 5000,
		singleItem: true,
		navigation: true,
		navigationText: ['<i class="fa fa-long-arrow-left fa1"></i>', '<i class="fa fa-long-arrow-right fa2"></i>'],
		pagination: true,
	});
	/*slideshow script code end here*/
	
	
	/*testimonails script code start here*/
	$('.testimonails').owlCarousel({
		items: 1,
		itemsDesktop : [1199, 1],
		itemsDesktopSmall : [979, 1],
		itemsTablet : [768, 1],
		itemsMobile : [479, 1],
		navigation : false,
		slideSpeed : 300,
		paginationSpeed : 400,
		singleItem : false,
		navigationText: ['<i class="fa fa-angle-left fa1"></i>', '<i class="fa fa-angle-right fa2"></i>'],
		pagination: true,
	});
	/*testimonails script code end here*/
	
	/*cause script code start here*/
	$('#cause').owlCarousel({
		items: 1,
		itemsDesktop : [1199, 1],
		itemsDesktopSmall : [979, 1],
		itemsTablet : [768, 1],
		itemsMobile : [479, 1],
		navigation : true,
		slideSpeed : 300,
		paginationSpeed : 400,
		singleItem : false,
		navigationText: ['<i class="arrow_left fa1"></i> Previous', 'Next <i class="arrow_right fa2"></i>'],
		pagination: false,
	});
	/*cause script code end here*/
	
	/*event script code start here*/
	$('#event').owlCarousel({
		items: 1,
		itemsDesktop : [1199, 1],
		itemsDesktopSmall : [979, 1],
		itemsTablet : [768, 1],
		itemsMobile : [479, 1],
		navigation : true,
		slideSpeed : 300,
		paginationSpeed : 400,
		singleItem : false,
		navigationText: ['<i class="arrow_left fa1"></i> Previous', 'Next <i class="arrow_right fa2"></i>'],
		pagination: false,
	});
	/*event script code end here*/
	
	/*doner script code start here*/
	$('.doner').owlCarousel({
		items: 2,
		itemsDesktop : [1199, 2],
		itemsDesktopSmall : [979, 1],
		itemsTablet : [768, 1],
		itemsMobile : [479, 1],
		navigation : true,
		slideSpeed : 300,
		paginationSpeed : 400,
		singleItem : false,
		navigationText: ['<i class="fa fa-angle-left fa1"></i>', '<i class="fa fa-angle-right fa2"></i>'],
		pagination: false,
	});
	/*doner script code end here*/
	
	
	//quantity code
	$(function () {
		$('.add').on('click',function(){
			var $qty=$(this).closest('p').find('.qty');
			var currentVal = parseInt($qty.val());
				$qty.val(currentVal + 1);
		});
		$('.minus').on('click',function(){
			var $qty=$(this).closest('p').find('.qty');
			var currentVal = parseInt($qty.val());
			$qty.val(currentVal - 1);					
		});
	});
	
	
	// Product List
	$('#list-view').on('click',function(){
		$('#content .row > .product-grid').attr('class', 'product-layout product-list col-xs-12');
		localStorage.setItem('display', 'list');
	});
	
	// Product Grid
	$('#grid-view').on('click',function(){
		$('#content .product-list').attr('class', 'product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12');
		localStorage.setItem('display', 'grid');
	});
	
	
	// collapse
	$('.collapse').on('shown.bs.collapse', function(){
	$(this).parent().removeClass("active").addClass("active");
	}).on('hidden.bs.collapse', function(){
	$(this).parent().removeClass("active").addClass("");
	});
	
	// collapse
	$('.collapse').on('shown.bs.collapse', function(){
	$(this).parent().find(".fa-plus").removeClass("fa-plus").addClass("fa-minus");
	}).on('hidden.bs.collapse', function(){
	$(this).parent().find(".fa-minus").removeClass("fa-minus").addClass("fa-plus");
	});
			
});
