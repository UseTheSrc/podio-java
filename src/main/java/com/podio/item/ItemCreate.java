package com.podio.item;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ItemCreate {

	/**
	 * The external id of the item. This can be used to hold a reference to the
	 * item in an external system.
	 */
	private String externalId;

	/**
	 * The values for each field
	 */
	private List<FieldValues> fields;

	/**
	 * Temporary files that have been uploaded and should be attached to this
	 * item
	 */
	private List<Integer> fileIds;

	/**
	 * The tags to put on the item
	 */
	private List<String> tags;

	public ItemCreate() {
		super();
	}

	public ItemCreate(String externalId, List<FieldValues> fields,
			List<Integer> fileIds, List<String> tags) {
		super();
		this.externalId = externalId;
		this.fields = fields;
		this.fileIds = fileIds;
		this.tags = tags;
	}

	@JsonProperty("external_id")
	public String getExternalId() {
		return externalId;
	}

	@JsonProperty("external_id")
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public List<FieldValues> getFields() {
		return fields;
	}

	public void setFields(List<FieldValues> fields) {
		this.fields = fields;
	}

	@JsonProperty("file_ids")
	public List<Integer> getFileIds() {
		return fileIds;
	}

	@JsonProperty("file_ids")
	public void setFileIds(List<Integer> fileIds) {
		this.fileIds = fileIds;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}