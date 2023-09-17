package com.app.suitcase.ui.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.suitcase.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewItemActivity extends AppCompatActivity {

    public static final String ITEM_NAME = "name";
    public static final String ITEM_PRICE = "price";
    public static final String ITEM_DESC = "desc";
    public static final String ITEM_IMAGE = "image";
    public static final String ITEM_MARK_AS_PURCHASED = "mark_as_purchased";

    private String imagePath;
    private EditText mName, mPrice, mDescription;
    private ImageView mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        mName = findViewById(R.id.name);
        mPrice = findViewById(R.id.price);
        mDescription = findViewById(R.id.desc);
        mImage = findViewById(R.id.image_view);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.new_item_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show the back button

        final Button saveBtn = findViewById(R.id.button_save);
        final Button galleryBtn = findViewById(R.id.button_choose_image);

        saveBtn.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mName.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String name = mName.getText().toString();
                String tempPrice = mPrice.getText().toString();
                if (tempPrice.isEmpty()) {
                    tempPrice = "0";
                }
                int price = Integer.parseInt(tempPrice);
                String description = mDescription.getText().toString();

                replyIntent.putExtra(ITEM_NAME, name);
                replyIntent.putExtra(ITEM_PRICE, price);
                replyIntent.putExtra(ITEM_DESC, description);
                replyIntent.putExtra(ITEM_IMAGE, imagePath);
                replyIntent.putExtra(ITEM_MARK_AS_PURCHASED, false); // TODO: mark as purchased

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        galleryBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            resultLauncher.launch(intent);
        });

    }

    private void animateAndFinish() {
        // Create a bundle for the shared elements
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair.create(findViewById(R.id.name), ViewCompat.getTransitionName(findViewById(R.id.name))),
                Pair.create(findViewById(R.id.price), ViewCompat.getTransitionName(findViewById(R.id.price))),
                Pair.create(findViewById(R.id.desc), ViewCompat.getTransitionName(findViewById(R.id.desc))),
                Pair.create(findViewById(R.id.image_view), ViewCompat.getTransitionName(findViewById(R.id.image_view)))
        ).toBundle();

        // Set the custom transition animation
        overridePendingTransition(R.anim.slide_to_right, android.R.anim.fade_out);

        // Finish the activity with the shared element transition
        finishAfterTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the Up button (back button) click
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Uri selectedImageUri = result.getData().getData();
                        imagePath = saveImageLocally(selectedImageUri);
                        if (imagePath != null) {
                            // Set the image to the ImageView
                            mImage.setImageURI(selectedImageUri);
                        }
                        break;
                }
            }
    );

    private String saveImageLocally(Uri imageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

            // Create a directory to store the image
            File directory = new File(getFilesDir(), "images");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique file name for the image
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String fileName = "IMG_" + timeStamp + ".jpg";

            // Create the image file
            File imageFile = new File(directory, fileName);
            imagePath = imageFile.getAbsolutePath();

            // Save the bitmap to the file
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos); // Adjust compression quality as needed
            fos.close();

            return imagePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}