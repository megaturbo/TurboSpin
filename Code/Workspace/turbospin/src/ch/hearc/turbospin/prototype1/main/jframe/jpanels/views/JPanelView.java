
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.views;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.media.j3d.Shape3D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;

public class JPanelView extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelView(TurboCanvas canvas, List<Shape3D> shapes)
		{
		this.canvas = canvas;
		geometry(shapes);
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry(List<Shape3D> shapes)
		{
		// JComponent : Instanciation
		panel3D = new JPanel3D(canvas);
		panelMain2D = new JPanelMain2D(shapes);
		canvas.setPanelMain2D(panelMain2D);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel3D, panelMain2D);

		splitPane.setOneTouchExpandable(true);

		// Layout : Specification
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		// JComponent : add

		add(splitPane, BorderLayout.CENTER);
		}

	private void control()
		{
		// rien
		addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent e)
					{
					//					splitPane.setDividerLocation(1.0 - getHeight() / (3.0 * getWidth()));
					splitPane.setDividerLocation(getWidth());
					}

			});
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("View"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	JSplitPane splitPane;
	JPanel3D panel3D;
	JPanelMain2D panelMain2D;
	TurboCanvas canvas;
	}
