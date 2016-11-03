<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <form:form method="post" commandName="item">
        <form:hidden path="id" />
        <fieldset class="form-group">
            <form:label path="name">Name of product</form:label>
            <form:input path="name" type="text" class="form-control"
                required="required" />
            <form:errors path="name" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="category">Category</form:label>
            <form:input path="category" type="text" class="form-control"
                required="required" />
            <form:errors path="category" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="quantity">Quantity</form:label>
            <form:input path="quantity" type="text" class="form-control"
                required="required" />
            <form:errors path="quantity" cssClass="text-warning" />
        </fieldset>
                <fieldset class="form-group">
            <form:label path="expDate">Expiration Date</form:label>
            <form:input path="expDate" type="text" class="form-control"
                required="required" />
            <form:errors path="expDate" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="btn btn-success">Submit</button>
    </form:form>
</div>

<%@ include file="common/footer.jspf"%>

<script>
    $('#expDate').datepicker({
        format : 'mm/dd/yyyy'
    });
</script>