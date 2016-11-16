<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">

    <table id="itemsTable" class="tablesorter">
        <thead>
        	<tr>
                <th>Name</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Expiration date</th>
                <th class="sorter-false"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category.name}</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${item.expDate}" /></td>
                    <td><a type="button" class="btn btn-primary" href="/update-item?id=${item.id}">Edit</a> 
                        <a type="button" class="btn btn-danger" href="/delete-item?id=${item.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
        	<tr>
        		<th colspan="4">
        			<div style="position:relative">
        				<div class="pager" style="">
        					<button type="button" class="btn prev"><</button>
        					<span class="pagedisplay"></span>
        					<button type="button" class="btn next">></button>
        				</div>
        				<div style="position:absolute; top: 5px"> 
        					<a type="button" class="btn btn-success" href="/add-item">  Add  </a>
    					</div>
        			</div>
        		</th>
        	</tr>
        </tfoot>
    </table>
</div>

<script>
	$(function() {
		$.tablesorter.themes.bootstrap = {
			table        : 'table table-striped table-hover',
			iconSortNone : 'bootstrap-icon-unsorted', // class name added to icon when column is not sorted
			iconSortAsc  : 'glyphicon glyphicon-chevron-up', // class name added to icon when column has ascending sort
			iconSortDesc : 'glyphicon glyphicon-chevron-down' // class name added to icon when column has descending sort
		};
		
		$("#itemsTable")
		.tablesorter({ 
			sortList: [[3,0]], 
			widthFixed: true, 
			widgets: ['uitheme','zebra'],
			theme: 'bootstrap',
			initWidgets: true,
			headerTemplate: '{content}{icon}',
			widgetOptions: {
				zebra: ["even", "odd"]
			}
		})
		.tablesorterPager({
			container: $(".pager"),
		    output: '{startRow} to {endRow}',
		    size: 5
		});
	});
</script>

<%@ include file="common/footer.jspf"%>
