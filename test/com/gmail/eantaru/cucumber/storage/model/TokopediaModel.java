package com.gmail.eantaru.cucumber.storage.model;

public class TokopediaModel {
	
	private String product;
	private String desc;
	private String image;
	private String price;
	private String rating;
	private String merchant;
	private String link;
		
	public String getProduct() {
		return product;
	}

	public String getDesc() {
		if(desc == null) desc = "";
		return desc;
	}

	public String getImage() {
		if(image == null) image = "";
		return image;
	}

	public String getPrice() {
		return price;
	}

	public String getRating() {
		if (rating == null) rating = "0";
		return rating;
	}

	public String getMerchant() {
		return merchant;
	}

	public String getLink() {
		return link;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public static class TokopediaModelBuilder {

		private String product; //name
		private String desc;
		private String image; //link
		private String price;
		private String rating; // out of 5 stars
		private String merchant; //name
		
		private String link; //detail page
		
		public TokopediaModelBuilder product(String product) {
			this.product = product;
			return this;
		}
		
		public TokopediaModelBuilder desc(String desc) {
			this.desc = desc;
			return this;
		}
		
		public TokopediaModelBuilder image(String image) {
			this.image = image;
			return this;
		}
		
		public TokopediaModelBuilder price(String price) {
			this.price = price;
			return this;
		}
		
		public TokopediaModelBuilder rating(String rating) {
			this.rating = rating;
			return this;
		}
	
		public TokopediaModelBuilder marchant(String merchant) {
			this.merchant = merchant;
			return this;
		}
	
		public TokopediaModelBuilder link(String link) {
			this.link = link;
			return this;
		}
		
		public TokopediaModel build() {
			TokopediaModel model = new TokopediaModel();
			model.product = this.product; 
			model.desc = this.desc;
			model.image = this.image; 
			model.price = this.price;
			model.rating = this.rating; 
			model.merchant = this.merchant; 
			model.link = this.link;
			return model;
		}
	}

}
