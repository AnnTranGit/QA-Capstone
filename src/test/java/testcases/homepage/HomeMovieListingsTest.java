package testcases.homepage;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.components.HomeMovieListingsSection;
import reports.ExtentReportManager;

@Listeners(TestListener.class)
public class HomeMovieListingsTest extends BaseTest {
    private HomeMovieListingsSection movieListing;
    private HomePage homePage;
    private final String movieTitle = "Man of Steel";

    @BeforeMethod
    public void setUpPages() {
         homePage = new HomePage();
        movieListing = homePage.movieListings();
    }


    @Test
    public void TC_Verify_MovieListing_Displays() {
        ExtentReportManager.info("Verify movie listing displays");
        LOG.info("Verify movie listing displays");

        homePage.getTopBarNavigation().clickShowtimes();
       // movieListing.scrollToMovieListing();
        Assert.assertTrue(movieListing.isMovieListingVisible(), "Movie listing section is not visible");
    }

    @Test
    public void TC_Verify_Hover_Shows_Action_Buttons() {
        ExtentReportManager.info("Hover shows action buttons successfully ");
        LOG.info("Hover shows action buttons successfully ");

        //movieListing.scrollToMovieListing();
       homePage.getTopBarNavigation().clickShowtimes();

        movieListing.hoverMovieCard(movieTitle);

        ExtentReportManager.info("Play / Buy Ticket Now buttons are visible after hover");
        Assert.assertTrue(movieListing.areActionButtonsVisible(movieTitle),
                "Play / Buy Ticket Now buttons are not visible after hover");
    }

    @Test
    public void TC_Verify_PlayButton_Opens_TrailerModal() {
        ExtentReportManager.info("Play button opens trailer modal");
        LOG.info("Play button opens trailer modal");

        movieListing.scrollToMovieListing();
       // homePage.getTopBarNavigation().clickShowtimes();

        movieListing.hoverMovieCard(movieTitle);
        movieListing.clickPlay(movieTitle);
        ExtentReportManager.info("Trailer modal is opened with video");
        Assert.assertTrue(movieListing.isTrailerModalOpened(), "Trailer modal is not opened or video is missing");
    }

    @Test
    public void TC_Verify_BuyButton_Redirects_To_Detail() {
        ExtentReportManager.info("Buy button redirects to detail");
        LOG.info("Buy button redirects to detail");

         movieListing.scrollToMovieListing();
      //  homePage.getTopBarNavigation().clickShowtimes();

        movieListing.hoverMovieCard(movieTitle);
        movieListing.clickBuyTicketNow(movieTitle);

        ExtentReportManager.info("Redirected to /detail URL successfully");
        Assert.assertTrue(driver.getCurrentUrl().contains("/detail/"),
                "Not redirected to /detail URL");
    }






    //-----------------------------------------------






//
//    @Test
//    public void Verify_hover_effect_on_movie_card_shows_overlay() {
//        ExtentReportManager.info("Hover on movie card should show overlay");
//        LOG.info("Hover on movie card should show overlay");
//
//        movieListings.hoverOnMovieCard("AVATAR 2");
//
//        Assert.assertTrue(movieListings.isOverlayVisibleOnCard("The Gentlemen"),
//                "Overlay is not visible after hovering the movie card");
//    }
//
//    @Test
//    public void Verify_clicking_play_button_opens_trailer_modal() {
//        ExtentReportManager.info("Hover then click Play should open trailer modal");
//        LOG.info("Hover then click Play should open trailer modal");
//
//        movieListings.clickPlayTrailerOnCard("The Gentlemen");
//
//
//        Assert.assertTrue(movieListings.isTrailerModalDisplayed(),
//                "Trailer modal is not displayed after clicking Play button");
//
//    }
//
//    @Test
//    public void Verify_clicking_buy_tickets_redirects_to_details_page() {
//        ExtentReportManager.info("Hover then click Buy Tickets should redirect to /details");
//        LOG.info("Hover then click Buy Tickets should redirect to /detail");
//
//        movieListings.clickBuyTicketsOnCard("The Gentlemen");
//
//        Assert.assertTrue(driver.getCurrentUrl().contains("/detail"),
//                "Not redirected to Movie Details page. Current URL: " + driver.getCurrentUrl());
//    }
}
