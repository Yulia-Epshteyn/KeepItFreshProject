<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <form:form method="post" commandName="setting">
        <form:hidden path="id" />
        <fieldset class="form-group">
            <form:label path="emailAddress">Enter your email address to get notifications</form:label>
            <form:input path="emailAddress" type="text" class="form-control"
                required="required" />
            <form:errors path="emailAddress" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="daysBeforeExp">Enter how many days before the expiration date you want to get a notification email</form:label>
            <form:input path="daysBeforeExp" type="text" class="form-control"
                required="required" />
            <form:errors path="daysBeforeExp" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="btn btn-success">Submit</button>
        <a href="/list-settings" class="btn btn-danger" role="button">Cancel</a>
    </form:form>
</div>

<%@ include file="common/footer.jspf"%>
