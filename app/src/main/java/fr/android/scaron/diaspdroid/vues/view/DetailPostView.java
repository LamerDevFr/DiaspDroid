package fr.android.scaron.diaspdroid.vues.view;

import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import org.acra.ACRA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.android.scaron.diaspdroid.controler.LogControler;
import fr.android.scaron.diaspdroid.controler.ProfilControler;
import fr.android.scaron.diaspdroid.model.Image;
import fr.android.scaron.diaspdroid.model.People;
import fr.android.scaron.diaspdroid.model.Post;
import fr.android.scaron.diaspdroid.vues.fragment.YoutubePlayerFragment;

/**
 * Created by Sébastien on 03/02/2015.
 */
public class DetailPostView extends Fragment {
    private static Logger LOGGEUR = LoggerFactory.getLogger(DetailPostView.class);
    private static LogControler LOG = LogControler.getLoggeur(LOGGEUR);
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    final String mimeType = "text/html";
    final String encoding = "utf-8";
    private Post post;

    public ImageView avatar;
    public TextView user;
    public TextView datetime;
    public TextView texte;
    public ImageView image;
    public WebView webvideo;
    public YouTubeThumbnailView thumbnail;
    public ImageView imgplay;
    public YoutubePlayerFragment youtubePlayerFragment;
    public RelativeLayout detailOpenGraph;
    public ImageView detailOpenGraphImg;
    public TextView detailOpenGraphTitle;
    public TextView detailOpenGraphTxt;
    public TextView detailOpenGraphWebSite;
    public TextView detailIndicsReshareText;
    public TextView detailIndicsLikeText;
    public TextView detailIndicsCommentaireText;

    public LinearLayout detailLike;
    public LinearLayout detailRepublish;
    public LinearLayout detailComment;

    public void setWebVideo(final String htmlSrc){
        LOG.d(".setWebVideo entree avec la valeur : "+htmlSrc);
        try {
            LOG.d(".setWebVideo find youtube fragment with id  R.id.webvideo");
            if (webvideo==null){
                LOG.d(".setWebVideo gloops webVideoFragment.webView is null");
            }else {
                LOG.d(".setWebVideo yeeppee webVideoFragment.webView is not null");
                webvideo.getSettings().setJavaScriptEnabled(true);
                webvideo.loadDataWithBaseURL(null, "<html><head><meta name=\"viewport\" content=\"width=device-width; user-scalable=no; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; target-densityDpi=device-dpi;\"/></head><body>" + htmlSrc + "</body></html>", mimeType, encoding, "");
                webvideo.setVisibility(View.VISIBLE);
                webvideo.setInitialScale(1);
                webvideo.getSettings().setJavaScriptEnabled(true);
                webvideo.getSettings().setLoadWithOverviewMode(true);
                webvideo.getSettings().setUseWideViewPort(true);
                webvideo.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                webvideo.setScrollbarFadingEnabled(false);
            }
        }catch(Throwable thr) {
            LOG.e(".setWebVideo sortie en Erreur ("+thr.toString()+")", thr);
            ACRA.getErrorReporter().handleException(thr);
            throw thr;
        }
        LOG.d(".setWebVideo sortie");
    }
    public void setPost(Post post) {
        LOG.d(".setPost entree with post : "+post);
        try {
                LOG.d(".setPost getSettings set params");
            // *** Entete
            // Remplissage de la partie auteur
            People author = post.getAuthor();
            if (author != null){
                Image iavatar = author.getAvatar();
                // Remplissage de l'avatar
                if (iavatar!=null){
                    ProfilControler.putImage(avatar, iavatar.getLarge());
                }
                // Remplissage du nom
                user.setText(author.getName());
            }
            // Remplissage de la date
            datetime.setText(post.getCreated_at_str());


            // *** Detail
            // Remplissage de la partie texte
            texte.setText(post.getText());
            // Remplissage de l'image
            String imageURL = post.getImage_url();
            if (imageURL!=null && !imageURL.isEmpty()){
                ProfilControler.putImage(image, imageURL);
            }

//            texte.loadDataWithBaseURL("file:///android_asset/", sbHtml.toString(), mimeType, encoding, "");
            LOG.d(".setPost sortie en succès");
        }catch(Throwable thr) {
            LOG.e(".setPost sortie en Erreur ("+thr.toString()+")", thr);
            ACRA.getErrorReporter().handleException(thr);
            throw thr;
        }
    }

}
