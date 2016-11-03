<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Email Address</th>
                <th>Number of days before expiration date</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${settings}" var="setting">
                <tr>
                    <td>${setting.emailAddress}</td>
                    <td>${setting.daysBeforeExp}</td>
                    <td><a type="button" class="btn btn-primary" href="/update-setting?id=${setting.id}">Edit</a> 
                        <a type="button" class="btn btn-warning" href="/delete-setting?id=${setting.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="/add-setting">Add</a>
    </div>
</div>
<%@ include file="common/footer.jspf"%>