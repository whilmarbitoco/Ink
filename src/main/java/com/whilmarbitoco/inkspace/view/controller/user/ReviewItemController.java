package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Review;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ReviewItemController {
    public ImageView img;
    public Label author;
    public Label date;
    public Label desc;
    public Label rating;

    private final UserRepository userRepository = new UserRepository();

    public void setReview(Review review) {
        userRepository.findByID(review.getUserID()).ifPresent(e -> {
            img.setImage(ImageHelper.load(e.getImage()));
            author.setText(e.getFullName());
        });

        date.setText(review.getCreated().getMonth() + " " + review.getCreated().getYear());
        desc.setText(review.getComment());
        rating.setText(review.getRating() + "/5");


    }
}
