package com.poli.programmingparadigms.mastermindinenglish.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Represents an animation target for flipping a card label.
 *
 * <p>
 * The {@code FlipAnimationTarget} class provides a flipping animation for a
 * {@code JLabel} representing a card. It rotates the card label to simulate a
 * flip effect.
 * </p>
 */
public class FlipAnimationTarget {

	private final JLabel label;
	private Timer timer;
	private int currentAngle = 0;

	/**
	 * Constructs a new {@code FlipAnimationTarget} for the specified label.
	 *
	 * @param label The label to apply the flip animation.
	 */
	public FlipAnimationTarget(JLabel label) {
		this.label = label;
		this.timer = new Timer(25, new TimerListener());
	}

	/**
	 * Starts the flip animation.
	 */
	public void startAnimation() {
		timer.start();
	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentAngle <= 270) {
				updateLabel();
				currentAngle += 30;
			} else {
				timer.stop();
				currentAngle = 0;
			}
		}

		private void updateLabel() {
			final ImageIcon originalIcon = (ImageIcon) label.getIcon();
			final Image originalImage = originalIcon.getImage();

			final int width = originalImage.getWidth(label);
			final int height = originalImage.getHeight(label);

			final Image rotatedImage = rotateImage(originalImage, Math.toRadians(180), width, height);

			final ImageIcon rotatedIcon = new ImageIcon(rotatedImage);
			label.setIcon(rotatedIcon);
		}

		private Image rotateImage(Image image, double angle, int width, int height) {
			final BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			final Graphics2D grahphic2d = rotatedImage.createGraphics();

			final AffineTransform transform = new AffineTransform();
			transform.rotate(angle, width / 2.0, height / 2.0);
			grahphic2d.setTransform(transform);

			grahphic2d.drawImage(image, 0, 0, label);

			grahphic2d.dispose();

			return rotatedImage;
		}
	}
}
