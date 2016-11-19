<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="row">
        <div class="col-md-5" id="search-container">
        	<input id="search" type="text" class="form-control input-lg" placeholder="Search the item" />
        </div>
	</div>
	<div class="row">
		<div class="mini-spacer"></div>
    	<table id="itemsTable" class="table table-striped">
        	<thead>
        		<tr>
                	<th>Name</th>
                	<th>Category</th>
                	<th>Quantity</th>
                	<th>Expiration date</th>
                	<th></th>
            	</tr>
        	</thead>
        	<tbody>
            	<c:forEach items="${items}" var="item">
                	<tr>
                    	<td class="col-md-3">${item.name}</td>
                    	<td class="col-md-3">${item.category.name}</td>
                    	<td class="col-md-2">${item.quantity}</td>
                    	<td class="col-md-2"><fmt:formatDate pattern="MM/dd/yyyy" value="${item.expDate}" /></td>
                    	<td class="col-md-2">
                    		<a type="button" class="btn btn-primary" href="/update-item?id=${item.id}">Edit</a> 
                        	<a type="button" class="btn btn-danger" href="/delete-item?id=${item.id}" data-toggle="confirmation">Delete</a>
                    	</td>
                	</tr>
            	</c:forEach>
        	</tbody>
        	<tfoot>
        		<tr>
        			<th>
        				<div style="position:relative">
        					<div style="position:absolute; top: 16px"> 
        						<a type="button" class="btn btn-success" href="/add-item">  Add  </a>
    						</div>
        				</div>
        			</th>
        		</tr>
        	</tfoot>
    	</table>
    </div>
</div>

<%@ include file="common/footer.jspf"%>
