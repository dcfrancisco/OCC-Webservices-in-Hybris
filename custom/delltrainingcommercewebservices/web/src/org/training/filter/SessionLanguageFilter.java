/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package org.training.filter;

import de.hybris.platform.core.model.c2l.LanguageModel;
import org.training.context.ContextInformationLoader;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * Filter sets language in session context basing on request parameters:<br>
 * <ul>
 * <li><b>lang</b> - set current {@link LanguageModel}</li>
 * </ul>
 */
public class SessionLanguageFilter extends OncePerRequestFilter
{
	private ContextInformationLoader contextInformationLoader;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException
	{
		getContextInformationLoader().setLanguageFromRequest(request);

		filterChain.doFilter(request, response);
	}

	protected ContextInformationLoader getContextInformationLoader()
	{
		return contextInformationLoader;
	}

	@Required
	public void setContextInformationLoader(final ContextInformationLoader contextInformationLoader)
	{
		this.contextInformationLoader = contextInformationLoader;
	}
}
