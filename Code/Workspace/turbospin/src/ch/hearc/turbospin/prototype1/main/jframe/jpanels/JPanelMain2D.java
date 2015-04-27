
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;

public class JPanelMain2D extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMain2D(List<Vector3D> vectors)
		{
		geometry(vectors);
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry(List<Vector3D> vectors)
		{
		// JComponent : Instanciation
		Map<Character, Vector3D> axes = new HashMap<Character, Vector3D>();
		axes.put('i', new Vector3D(1, 0, 0, TurboColors.RED));
		axes.put('j', new Vector3D(0, 1, 0, TurboColors.GREEN));
		axes.put('k', new Vector3D(0, 0, 1, TurboColors.BLUE));
		panelIJ = new JPanel2D(vectors, 'i', 'j', axes);
		panelJK = new JPanel2D(vectors, 'j', 'k', axes);
		panelKI = new JPanel2D(vectors, 'k', 'i', axes);

		// Layout : Specification
		GridLayout gridLayout = new GridLayout(0, 1);
		setLayout(gridLayout);

		//Creating intermediary panels so that the JPanel2Ds don't step on the borders
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("ij"));
		pan2.setBorder(BorderFactory.createTitledBorder("jk"));
		pan3.setBorder(BorderFactory.createTitledBorder("ki"));
		pan1.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		pan3.setLayout(new BorderLayout());
		add(pan1);
		add(pan2);
		add(pan3);

		// JComponent : add
		pan1.add(panelIJ, BorderLayout.CENTER);
		pan2.add(panelJK, BorderLayout.CENTER);
		pan3.add(panelKI, BorderLayout.CENTER);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		this.setBackground(Color.BLUE);
		setBorder(BorderFactory.createTitledBorder("2D"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	JPanel2D panelIJ;
	JPanel2D panelJK;
	JPanel2D panelKI;

	//	Box boxV;
	}
