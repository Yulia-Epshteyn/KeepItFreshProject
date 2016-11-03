<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <table class="table table-striped">
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
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${item.expDate}" /></td>
                    <td><a type="button" class="btn btn-primary" href="/update-item?id=${item.id}">Edit</a> 
                        <a type="button" class="btn btn-warning" href="/delete-item?id=${item.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="/add-item">Add</a>
    </div>
</div>
<%@ include file="common/footer.jspf"%>