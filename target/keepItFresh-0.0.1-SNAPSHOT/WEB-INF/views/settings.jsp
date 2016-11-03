<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <form:form method="post" commandName="settings">
        <form:hidden path="id" />
        <fieldset class="form-group">
            <form:label path="desc">Add a new category</form:label>
            <form:input path="desc" type="text" class="form-control"
                required="required" />
            <form:errors path="desc" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">Add an email to get a notification</form:label>
            <form:input path="targetDate" type="text" class="form-control"
                required="required" />
            <form:errors path="targetDate" cssClass="text-warning" />
        </fieldset>
                <fieldset class="form-group">
            <form:label path="targetDate">Set pre-expiration notification date</form:label>
            <form:input path="targetDate" type="text" class="form-control"
                required="required" />
            <form:errors path="targetDate" cssClass="text-warning" />
        </fieldset>
        
        <button type="submit" class="btn btn-success">Submit</button>
    </form:form>
</div>

<%@ include file="common/footer.jspf"%>

<script>
    $('#targetDate').datepicker({
        format : 'dd/mm/yyyy'
    });
</script>

