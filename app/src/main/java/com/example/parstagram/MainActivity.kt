package com.example.parstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

/**
 * Let user create a post by taking a photo with their camera
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Set the description of the post
        // 2. A button to launch the camera to take a picture
        // 3. An ImageView to show the picture the user has taken
        // 4. A button to save and send the post to our Parse server

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            // send post to server without an image
            // get the description that they have inputted
            val description = findViewById<EditText>(R.id.etDescription).text.toString()
            val user = ParseUser.getCurrentUser()
            submitPost(description, user)
        }


        queryPosts()
    }

    // send a post object to our Parse server
    fun submitPost(description: String, user: ParseUser) {
        // create the Post object
        val post = Post()
        post.setDescription(description)
        post.setUser(user)
        post.saveInBackground { exception ->
            if (exception != null) {
                // something has went wrong
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
                // TODO: Show a toast to tell user something went wrong with saving post
            } else {
                Log.i(TAG, "Successfully saved post")
                // TODO: Reset the EditText field to be empty
                // TODO: Reset the ImageView to empty
            }
        }
    }

    // Query for all posts in our server
    fun queryPosts() {

        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

        // Find all Post objects
        query.include(Post.KEY_USER)
        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription())
                        }
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}