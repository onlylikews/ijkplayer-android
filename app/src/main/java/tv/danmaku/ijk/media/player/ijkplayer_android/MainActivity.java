package tv.danmaku.ijk.media.player.ijkplayer_android;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, MeasureHelper.MeasureFormVideoParamsListener {

	TextureView textureView;
	IjkMediaPlayer ijkMediaPlayer;
	MeasureHelper measureHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FrameLayout rootView = new FrameLayout(this);
		textureView = new TextureView(this) {
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				measureHelper.prepareMeasure(widthMeasureSpec, heightMeasureSpec, (int) getRotation());
				setMeasuredDimension(measureHelper.getMeasuredWidth(), measureHelper.getMeasuredHeight());
			}
		};
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.CENTER;
		rootView.addView(textureView, layoutParams);
		setContentView(rootView);
		textureView.setSurfaceTextureListener(this);

		measureHelper = new MeasureHelper(textureView, this);

		ijkMediaPlayer = new IjkMediaPlayer();
		ijkMediaPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
			@Override
			public boolean onInfo(IMediaPlayer mp, int what, int extra) {
				if (IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED == what) {
					textureView.setRotation(extra);
					textureView.requestLayout();
				}
				return false;
			}
		});
	}


	private void playVideo(Surface surface) {
		String filePath = "/storage/emulated/0/DCIM/Camera/VID_20190119_184109.mp4";
//		String filePath = "http://link-test.bingosoft.net/store/store/getFile?fileId=%403d05b1434a2e462cb97c6845b487c043%2C548e236d5ca64ed5b1fade945c%2471efe0&access_token=bG9jYWw6MWVjZjZiZWItNWNjNC00MzlmLWIxY2MtYTZmYzRiYTcxZjJj";
		try {
			ijkMediaPlayer.setSurface(surface);
			ijkMediaPlayer.setDataSource(filePath);
			ijkMediaPlayer.prepareAsync();
			ijkMediaPlayer.start();
			ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(IMediaPlayer mp) {
					textureView.requestLayout();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		playVideo(new Surface(surface));
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		return false;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {

	}

	@Override
	public int getCurrentVideoWidth() {
		return ijkMediaPlayer.getVideoWidth();
	}

	@Override
	public int getCurrentVideoHeight() {
		return ijkMediaPlayer.getVideoHeight();
	}

	@Override
	public int getVideoSarNum() {
		return 0;
	}

	@Override
	public int getVideoSarDen() {
		return 1;
	}
}