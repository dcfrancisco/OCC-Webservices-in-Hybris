/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.DelltrainingcommercewebservicesConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.DelltrainingcommercewebservicesConstants;
import org.training.service.DelltrainingcommercewebservicesService;


@SystemSetup(extension = DelltrainingcommercewebservicesConstants.EXTENSIONNAME)
public class DelltrainingcommercewebservicesSystemSetup
{
	private final DelltrainingcommercewebservicesService delltrainingcommercewebservicesService;

	public DelltrainingcommercewebservicesSystemSetup(final DelltrainingcommercewebservicesService delltrainingcommercewebservicesService)
	{
		this.delltrainingcommercewebservicesService = delltrainingcommercewebservicesService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		delltrainingcommercewebservicesService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return DelltrainingcommercewebservicesSystemSetup.class.getResourceAsStream("/delltrainingcommercewebservices/sap-hybris-platform.png");
	}
}
