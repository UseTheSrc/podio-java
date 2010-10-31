package com.podio.app;

import java.util.Arrays;

import junit.framework.Assert;

import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

import com.podio.BaseAPIFactory;

public class AppAPITest {

	private AppAPI getAPI() {
		return new AppAPI(BaseAPIFactory.get());
	}

	@Test
	public void getAppFull() throws JSONException {
		Application app = getAPI().getApp(1);

		Assert.assertEquals(app.getId(), 1);
		Assert.assertNotNull(app.getConfiguration());
		Assert.assertEquals(app.getConfiguration().getDefaultView(),
				ApplicationViewType.BADGE);

		Assert.assertEquals(app.getFields().size(), 10);
		ApplicationField stateField = app.getFields().get(0);
		Assert.assertEquals(stateField.getType(), ApplicationFieldType.STATE);
		Assert.assertEquals(stateField.getConfiguration().getLabel(),
				"Is hired?");
	}

	@Test
	public void getAppShort() throws JSONException {
		Application app = getAPI().getApp(1, "short");

		Assert.assertEquals(app.getId(), 1);
		Assert.assertNotNull(app.getConfiguration());
		Assert.assertEquals(app.getConfiguration().getDefaultView(),
				ApplicationViewType.BADGE);

		Assert.assertNull(app.getFields());
	}

	@Test
	public void addApp() {
		ApplicationCreateResponse response = getAPI().addApp(
				new ApplicationCreate(1, true, true,
						new ApplicationConfiguration("Tests", "Test",
								"Description", "Usage", "ExternalId", "23.png",
								true, true, ApplicationViewType.BADGE, true,
								true, true, false, null, false, false, null,
								false, null, false, null, Arrays.asList(
										"Task 1", "Task 2")), Arrays
								.asList(new ApplicationFieldCreate(
										ApplicationFieldType.TITLE,
										new ApplicationFieldConfiguration(
												"title", "Title", 0, null,
												true, true)))));
		Assert.assertTrue(response.getId() > 0);
	}

	@Test
	public void updateApp() {
		getAPI().updateApp(
				1,
				new ApplicationUpdate(true, new ApplicationConfiguration(
						"Tests", "Test", "Description", "Usage", "ExternalId",
						"23.png", true, true, ApplicationViewType.BADGE, true,
						true, true, false, null, false, false, null, false,
						null, false, null, Arrays.asList("Task 1", "Task 2")),
						Arrays.asList(new ApplicationFieldUpdate(1,
								new ApplicationFieldConfiguration("hired",
										"Is hired?", 10,
										ApplicationFieldSettings
												.getState(Arrays.asList("yes",
														"no", "maybe")), true,
										true)))));
	}

	@Test
	public void getField() {
		ApplicationField field = getAPI().getField(1, 1);

		Assert.assertEquals(field.getId(), 1);
		Assert.assertEquals(field.getType(), ApplicationFieldType.STATE);
		Assert.assertEquals(field.getConfiguration().getName(), "hired");
		Assert.assertEquals(field.getConfiguration().getLabel(), "Is hired?");
		Assert.assertEquals(field.getConfiguration().getDelta(), 0);
		Assert.assertEquals(field.getConfiguration().getSettings()
				.getAllowedValues(), Arrays.asList("yes", "no"));
	}

	@Test
	public void addField() {
		ApplicationFieldCreateResponse response = getAPI().addField(
				1,
				new ApplicationFieldCreate(ApplicationFieldType.LARGE_TEXT,
						new ApplicationFieldConfiguration("test",
								"Description", 0, null, true, true)));
		Assert.assertTrue(response.getId() > 10);
	}

	@Test
	public void updateField() {
		getAPI().updateField(
				1,
				1,
				new ApplicationFieldConfiguration("hired", "Is hired?", 10,
						ApplicationFieldSettings.getState(Arrays.asList("yes",
								"no", "maybe")), true, true));
	}

	@Test
	public void deleteField() {
		getAPI().deleteField(1, 1);
	}

	@Test
	public void installApp() {
		ApplicationCreateResponse result = getAPI().install(1,
				new ApplicationInstall(1));
		Assert.assertTrue(result.getId() > 10);

	}
}
