package com.product.api;

public class ProductPOJO {

	private int id;
	private String title;
	private Object price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	public ProductPOJO() {
	}

	public ProductPOJO(int id, String title, Object price, String description, String category, String image,
			Rating rating) {

		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getPrice() {
		return price;
	}

	public void setPrice(Object price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public static class Rating {

		private Object rate;
		private int count;

		public Rating() {
		}

		public Rating(Object rate, int count) {

			this.rate = rate;
			this.count = count;
		}

		public Object getRate() {
			return rate;
		}

		public void setRate(Object rate) {
			this.rate = rate;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

}
