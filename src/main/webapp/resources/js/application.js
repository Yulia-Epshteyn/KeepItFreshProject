$(function() {
//	    $('#search').quicksearch('table tbody tr', {
//		      selector: 'td:first-child',
//		      prepareQuery: function (val) {
//		    	  return new RegExp('^' + val, 'i');
//		      },
//		      testQuery: function (query, txt, _row) {
//		    	  return query.test(txt);
//		      }
//		    });
	    
	    var dataTable = $('#itemsTable').DataTable({
	    	sDom: '<t<p>>',
	    	order: [[ 3, 'asc' ]],
	    	columnDefs: [
	    	  {
	    		  targets: [ 1, 2, 3, 4 ],
	    		  searchable: false
	    	  },
	    	  {
	    		  targets: [ 4 ],
	    		  orderable: false
	    	  }
	    	]
	    });
	    
		$('#search').on('keyup search input paste cut', function() {
			dataTable.search('^' + this.value, true, false).draw();
		});
	    
//		$.tablesorter.themes.bootstrap = {
//			table        : 'table table-striped table-hover',
//			iconSortNone : 'bootstrap-icon-unsorted', // class name added to icon when column is not sorted
//			iconSortAsc  : 'glyphicon glyphicon-chevron-up', // class name added to icon when column has ascending sort
//			iconSortDesc : 'glyphicon glyphicon-chevron-down' // class name added to icon when column has descending sort
//		};
//		
//		$('#itemsTable')
//		.tablesorter({ 
//			sortList: [[3,0]], 
//			widthFixed: true, 
//			widgets: ['uitheme','zebra'],
//			theme: 'bootstrap',
//			initWidgets: true,
//			headerTemplate: '{content}{icon}',
//			widgetOptions: {
//				zebra: ['even', 'odd']
//			}
//		})
//		.bind('pagerChange pagerComplete pagerInitialized pageMoved', function(e, c){
//			var msg = '"</span> event triggered, ' + (e.type === 'pagerChange' ? 'going to' : 'now on') +
//				' page <span class="typ">' + (c.page + 1) + '/' + c.totalPages + '</span>';
//			$('#display')
//				.append('<li><span class="str">"' + e.type + msg + '</li>')
//					.find('li:first').remove();
//		})
//		.tablesorterPager({
//			container: $('.pager'),
//		    output: '{startRow:input} to {endRow} ({totalRows})',
//		    updateArrows: true,
//		    page: 0,
//		    size: 5,
//		    removeRows: false,
//		    cssNext: '.next',
//		    cssPrev: '.prev',
//		});
//		
		$('[data-toggle="confirmation"]').confirmation({
			singleton: true,
			placement: 'right',
			btnOkClass: 'btn-xs btn-success',
			btnCancelClass: 'btn-xs btn-danger'
		});
	});