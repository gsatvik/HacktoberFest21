import java.util.*;

class BlogPost {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date date;
    private List<String> categories;
    private List<String> comments;

    public BlogPost(int id, String title, String content, String author, List<String> categories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = new Date();
        this.categories = categories;
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }
}

class Blog {
    private List<BlogPost> posts = new ArrayList<>();

    public void addPost(BlogPost post) {
        posts.add(post);
    }

    public List<BlogPost> getPostsByCategory(String category) {
        return posts.stream()
            .filter(post -> post.getCategories().contains(category))
            .collect(Collectors.toList());
    }

    public void displayBlogPost(int postId) {
        BlogPost post = posts.stream()
            .filter(p -> p.getId() == postId)
            .findFirst()
            .orElse(null);

        if (post != null) {
            System.out.println("Title: " + post.getTitle());
            System.out.println("Author: " + post.getAuthor());
            System.out.println("Date: " + post.getDate());
            System.out.println("Content: " + post.getContent());
            System.out.println("Categories: " + String.join(", ", post.getCategories()));
            System.out.println("Comments:");
            for (String comment : post.getComments()) {
                System.out.println("- " + comment);
            }
        } else {
            System.out.println("Blog post not found.");
        }
    }
}

public class ContentManagementSystem {
    public static void main(String[] args) {
        Blog blog = new Blog();

        // Creating and adding blog posts
        BlogPost post1 = new BlogPost(1, "Introduction to Java", "This is an introductory post about Java.", "Alice", Arrays.asList("Programming", "Java"));
        BlogPost post2 = new BlogPost(2, "Web Development Basics", "A primer on web development technologies.", "Bob", Arrays.asList("Web Development", "Programming"));

        blog.addPost(post1);
        blog.addPost(post2);

        // Adding comments to blog posts
        post1.addComment("Great post!");
        post2.addComment("I found this very helpful.");

        // Displaying a blog post
        System.out.println("Displaying Blog Post:");
        blog.displayBlogPost(1);

        // Retrieving posts by category
        List<BlogPost> programmingPosts = blog.getPostsByCategory("Programming");
        System.out.println("\nProgramming Posts:");
        for (BlogPost post : programmingPosts) {
            System.out.println("- " + post.getTitle());
        }
    }
}
