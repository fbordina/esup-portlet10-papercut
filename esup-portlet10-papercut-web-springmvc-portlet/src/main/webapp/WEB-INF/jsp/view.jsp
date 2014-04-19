<%@ include file="/WEB-INF/jsp/include.jsp"%>

<portlet:renderURL var="renderRefreshUrl" />

<div class="portlet-title">
  <h2>
    <spring:message code="index.title"/>
  </h2>
</div>

<div class="portlet-section">


  <div class="portlet-section-body">
  
  		<div class="portlet-section-body">
			<c:out value="${htmlHeader}" escapeXml="false"/>
		</div>
			<p>
				<spring:message code="index.fullName"/> : <b>${userPapercutInfos.fullName} (${userPapercutInfos.uid})</b>
			</p>
			<p>
				<spring:message code="index.sold"/> : <b>${userPapercutInfos.balance} EUR</b>
			</p>
			<p>
				<spring:message code="index.printJobs"/> : <b>${userPapercutInfos.printJobs}</b>
			</p>
			<p>
				<spring:message code="index.printPages"/> : <b>${userPapercutInfos.printPages}</b>
			</p>


				<!-- <div class="portlet-section-body">
			
					<h3>Autres infos ...</h3>
			
					<dl>
						<dt><spring:message code="index.email"/></dt>
						<dd>${userPapercutInfos.email}</dd>
						<dt><spring:message code="index.department"/></dt>
						<dd>${userPapercutInfos.department}</dd>
						<dt><spring:message code="index.office"/></dt>
						<dd>${userPapercutInfos.office}</dd>
						<dt><spring:message code="index.cardNumber"/></dt>
						<dd>${userPapercutInfos.cardNumber}</dd>
						<dt><spring:message code="index.printDisabled"/></dt>
						<dd>${userPapercutInfos.printDisabled}</dd>
						<dt><spring:message code="index.restricted"/></dt>
						<dd>${userPapercutInfos.restricted}</dd>
						<dt><spring:message code="index.notes"/></dt>
						<dd>${userPapercutInfos.notes}</dd>
					</dl>
			 	
				</div>-->
	
		<div class="portlet-section-body">
			<a href="${papercutServerUrl}" target="_blank">Mon compte en d�tail...</a>
			<c:out value="${htmlFooter}" escapeXml="false"/>
		</div>
  </div>

</div>

