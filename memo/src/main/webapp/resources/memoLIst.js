$(document).on('click', '.new_row', function(event){
	  var html_string = '<li><h4><input type="text" /></h4><button class="insert_done">입력완료</button></li>';
	  $('ul').append(html_string);
	});

$(document).on('click', '.insert_done', function(event){
	  var memo = $(this).parents('li').find('h4').find('input').val();
	  console.log(memo);
	  $.ajax({
	    url:'/memo_insert',
	    data: {'memo': memo},
	    success:function(data){
	      location.reload();
	      //  console.log(data);
	    }
	  });
	});

$(function(){
	$.ajax({
		url:'/select_memo_list',
		success:function(data){
			for(var i = 0; i < data.length; i++) {
//		        $html_string = '<ul>';
		        $html_string = '<li><h3>'+ data[i]['no']+'</h3>';
		        $html_string = $html_string  +'<h4>'+ data[i]['memo']+ '</h4>'+'<button class="mod_row">수정하기</button><button class="mod_done">수정완료</button><button class="del_row">삭제하기</button>' +'</li>';
//		        $html_string = $html_string + '</ul>';
		        $('ul').append($html_string);
		      }
		        console.log(data);
		}
	});
});


$(document).on('click', '.mod_row', function(event){
	  var row_string = $(this).parents('li').find('h4').text();
	  var row = $(this).parents('li').find('h4')
	  var find_li = $(this).parents('li')
	  console.log("1:"+row)
	  console.log("2:"+row_string)
//	  var row_string = row.find('input').val();
//	  console.log("2:"+row_stirng)

	  var input_tag = $('<input type="text" />');
	  row.html(input_tag);
	  input_tag.val(row_string);

	  find_li.find('.mod_row').hide();
	  find_li.find('.mod_done').show();
	});

$(document).on('click', '.del_row', function(event){
	  var no = $(this).parents('li').find('h3').text();
	  console.log(no)
	  var memo = $(this).parents('li').find('h4').text();
	  console.log(memo)
	  $.ajax({
	    url:'/memo_del',
	    data: {'no': no},
	    success:function(data){
	      location.reload();
	      //  console.log(data);
	    }
	  });
	});

$(document).on('click', '.mod_done', function(event){
	  var no = $(this).parents('li').find('h3').text();
	  console.log(no)
	  var memo = $(this).parents('li').find('h4').find('input').val();
	  console.log(memo)
	  $.ajax({
	    url:'/memo_mod',
	    data: {'no': no, 'memo': memo},
	    success:function(data){
	      location.reload();
	      //  console.log(data);
	    }
	  });
	});

