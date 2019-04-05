$(function(){
	//solving the active menu problem
	switch (menu){
	
	case 'About':
		System.out.println('Inside product!');
		$('#about').addClass('active');
		break;
		
	case 'Contact Us':
		$('#contact').addClass('active');
		break;	
		
	case 'All Products':
		$('#listProducts').addClass('active');
		break;	
	
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;	
	
	default:
		if(menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
	break;	
	
	}
		
//	Code For jquery dataTable
	
//	To tackle the csrf token problem
	var token=$('meta[name="_csrf"]').attr('content');
	var header=$('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
//		Set the token header for the ajax request
		$(document).ajaxSend(function(e,xhr,options){
			
			xhr.setRequestHeader(header,token);
		});
		
		
	}
	
//	Create a variable set
	

	var $table=$('#productListTable');
//	Execute this table where we have this table
	if($table.length){
    var jsonUrl='';
    if(window.categoryId==''){
    	jsonUrl=window.contextRoot+'/json/data/all/products';
    }
    else{
    	jsonUrl=window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
    }
		$table.DataTable({
			lengthMenu : [[3,5,10,-1],['3 Records','5 Records','10 Records','All Records']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc:''
			},
		columns: [
			{
				data:'code',
				mRender: function(data,type,row){
					
					return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
				}
					
				
			},
			{
				data: 'name'
			},
			{
				data: 'brand'
			},
			{
				data: 'unitPrice',
				mRender: function(data,type,row) {
					return '&#8377; '+data
				}
			},
			{
				data: 'quantity',
				mRender: function(data,type,row){
					
					if(data<1){
						return '<span style="color:red">OUT OF STOCK</span>';
					}
					else{
						return data;
					}
				}
			},
			{
				data: 'id',
				bSortable: false,
				mRender: function(data,type,row) {
					var str='';
					str +='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary">View</a> &#160;';
					if(userRole=='ADMIN'){
						str +='<a href="'+window.contextRoot+'/manage/'+data+'/products" class="btn btn-warning" >Edit</a>';
					}
					else{
						if(row.quantity<1){
							str +='<a href="javascript:void(0)" class="btn btn-success disabled" >Add to cart</a>';
						}
						else{
							str +='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success" >Add to cart</a>';		
							}
							
						}
					
					
					
					/*str +='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success" >Add to cart</a>';*/
					return str;
				}
				
			}
			
		]
		});
	}
	
//	Dismissing after 3 seconds
	var $alert=$('.alert');
	if($alert.length){
		
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000)
	}
	
	/*----------------------
	*/
	
	$('.switch input[type="checkbox"]').on('change',function(){
		
		var checkbox=$(this);
		var checked=checkbox.prop('checked');
		var dMsg=(checked)? 'You want to activate the product':
							'You want to deactivate the product';
		var value=checkbox.prop('value');
		
		bootbox.confirm({
			size: 'medium',
			title: ' Product Activation and Deactivation ',
			message: dMsg,
			callback: function(confirmed){
				if(confirmed){
					console.log(value);
					bootbox.alert({
						size: 'medium',
						title:'Product Activation Deactivation',
						message: 'You are going to perform action in product'+value						
					});
					
				}
				else{
					checkbox.prop('checked', !checked);
				}
			}
			
		});
		
		
	});
	
	/* DataTable for Admin
	*/
	var $adminProductsTable=$('#adminProductsTable');
//	Execute this table where we have this table
	if($adminProductsTable.length){
    var jsonUrl=window.contextRoot+'/json/data/admin/all/products';
   
		$adminProductsTable.DataTable({
			lengthMenu : [[10,30,50,-1],['10 Records','30 Records','50 Records','All Records']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc:''
			},
		columns: [
			{
				data:'id'
			},
			{
				data:'code',
				mRender: function(data,type,row){
					
					return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
				}
					
				
			},
			{
				data: 'brand'
			},
			{
				data: 'name'
			},
			
			{
				data: 'quantity',
				mRender: function(data,type,row){
					
					if(data<1){
						return '<span style="color:red">OUT OF STOCK</span>';
					}
					else{
						return data;
					}
				}
			},
			{
				data: 'unitPrice',
				mRender: function(data,type,row) {
					return '&#8377; '+data
				}
			},
			{
				data: 'active',
				bSortable: false,
				mRender: function(data,type,row){
					var str='';
					str += '<label class="switch">';
					if(data){
						str += '<input type="checkbox" checked="checked" value="'+row.id+'"/>';	
					}
					else{
						str += '<input type="checkbox" value="'+row.id+'"/>';
					}
					
					str += '<span class="slider"></span></label>';
					return str;
				}					
			},
			{
				data: 'id',
				bSortable: false,
				mRender: function(data,type,row){
					var str ='';
					str += '<a href="'+window.contextRoot+'/manage/'+data+'/products" class="btn btn-warning"></a>';
					
					return str;
				}
			}
			
		],
		initComplete: function(){
			var api=this.api();
			api.$('.switch input[type="checkbox"]').on('change',function(){
				
				var checkbox=$(this);
				var checked=checkbox.prop('checked');
				var dMsg=(checked)? 'You want to activate the product':
									'You want to deactivate the product';
				var value=checkbox.prop('value');
				
				bootbox.confirm({
					size: 'medium',
					title: ' Product Activation and Deactivation ',
					message: dMsg,
					callback: function(confirmed){
						if(confirmed){
							console.log(value);
							var activationUrl=window.contextRoot+'/manage/product/'+value+'/activation';
							$.post(activationUrl,function(data){
								bootbox.alert({
									size: 'medium',
									title:'Product Activation Deactivation',
									message: data					
								});
								
							});
							
							
						}
						else{
							checkbox.prop('checked', !checked);
						}
					}
					
				});
				
				
			});
			
		}
		});
	}
	
	/*-------------------
	*/
	/*Validation Form for Category
	*/
	var $categoryForm= $('#categoryForm');
	
	if($categoryForm.length){
		
		$categoryForm.validate({
			
			rules:{
				name:{
					required: true,
					minlength: 2
				},
				description:{
					required: true
				},
				messages:{
					name:{
						required: 'Please add Category Name',
						minlength: 'The name should not be less than 2 characters'
					},
					description:{
						required: 'Please add the description'
					}
					
				},
				errorElement:'em',
				errorPlacement: function(error,element){
//					Add the class of help-block
					error.addClass('help-block');
//					Add the error element after the input element
					error.insertAfter(element);
				}
				
				
			}
			
		});
		
	}
	
	/*---------------------
	*/
	
	/*Validation form for Login*/
var $loginForm= $('#loginForm');
	
	if($loginForm.length){
		
		$loginForm.validate({
			
			rules:{
				username:{
					required: true,
					email: true
				},
				password:{
					required: true
				},
				messages:{
					name:{
						required: 'Please enter the username!',
						email: 'Please enter the email address!'
					},
					password:{
						required: 'Please enter the password!'
					}
					
				},
				errorElement:'em',
				errorPlacement: function(error,element){
//					Add the class of help-block
					error.addClass('help-block');
//					Add the error element after the input element
					error.insertAfter(element);
				}
				
				
			}
			
		});
		
	}
	
	/*---------------*/
});