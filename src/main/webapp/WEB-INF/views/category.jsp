<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <form:form method="post" commandName="category">
        <form:hidden path="id" />
        <fieldset class="form-group">
            <form:label path="name">Enter a name to create a new category</form:label>
            <form:input path="name" type="text" class="form-control"
                required="required" />
            <form:errors path="name" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="btn btn-success">Submit</button>
        <a href="/list-settings" class="btn btn-danger" role="button">Cancel</a>
    </form:form>
</div>

<%@ include file="common/footer.jspf"%>
