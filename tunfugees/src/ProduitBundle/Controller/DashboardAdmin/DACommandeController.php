<?php
/**
 * Created by PhpStorm.
 * User: Aziz
 * Date: 24/02/2019
 * Time: 14:24
 */

namespace ProduitBundle\Controller\DashboardAdmin;
/**
 *
 * @Route("da")
 */
use Ob\HighchartsBundle\Highcharts\Highchart;
use ProduitBundle\Entity\Commande;
use ProduitBundle\Entity\LigneCommande;
use Doctrine\DBAL\Exception\UniqueConstraintViolationException;
use ProduitBundle\Entity\PanierProduit;
use ProduitBundle\Entity\Produits;
use UserBundle\Entity\User;
use ProduitBundle\Entity\Categorie;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Config\Definition\Exception\Exception;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DACommandeController extends Controller
{
    /**
     *
     * @Route("/commande", name="da_show_commande")
     * @Method("GET")
     */
    public function showAction()
    {
        $em=$this->getDoctrine()->getManager();
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        $users = $em->getRepository('UserBundle:User')->findAll();
        return $this->render('@Produit/DashboardAdmin/Commande/da_page_commande.html.twig', array(
            'commandes'=> $commandes,'users'=> $users
        ));
    }
    /**
     * Finds and displays a user entity.
     *
     * @Route("/show_ligne2/{id_c}", name="ligne_show2")
     * @Method("GET")
     */
    public function showLigneAction($id_c)
    {
        $em =$this->getDoctrine()->getManager();
        $L=$em->getRepository(PanierProduit::class)->findAll();

        $annonces = $em->getRepository('ProduitBundle:Produits')->findAll();
        $liste_ligne = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
        $tab=array('id_commande' => $id_c);

        return $this->render('@Produit/DashboardAdmin/Commande/da_detail_commande.html.twig',array(
            'liste_ligne'=> $liste_ligne,'annonces'=>$annonces,'tab'=>$tab));


    }

    /**
     * @Route("/annuler_commande2/{id_c}", name="annuler_commande2")
     * @Method("GET")
     */
    public function viderAction($id_c)
    {
        $em=$this->getDoctrine()->getManager();
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        $lignes = $em->getRepository('ProduitBundle:LigneCommande')->findAll();

        foreach ($commandes as $value)
        {
            if($value->getId()==$id_c)
            {
                foreach ($lignes as $v)
                {
                    if($v->getIdCommande()==$id_c)
                    {
                        $em->remove($v);
                        $em->flush();
                    }
                }
                $em->remove($value);
                $em->flush();
            }
        }
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        return $this->render('@Produit/DashboardAdmin/Commande/da_page_commande.html.twig', array(
            'commandes'=> $commandes
        ));
    }
    /**
     * @Route("/stat", name="stat_commande")
     * @Method("GET")
     */
    public function statAction()
    {


        $em = $this->getDoctrine()->getManager();
        //nb1
        $RAW_QUERY = 'SELECT  COUNT(*) as nb1 from commande where (date_emission >= \'2020-01-01\') AND (date_emission <= \'2020-01-31\');';
        $statement = $em->getConnection()->prepare($RAW_QUERY);
        $statement->execute();
        $nb1 = $statement->fetch();
        //nb2
        $RAW_QUERY2 = 'SELECT  COUNT(*) as nb2  from commande where (date_emission >= \'2020-02-01\') AND (date_emission <= \'2020-02-31\');';
        $statement2 = $em->getConnection()->prepare($RAW_QUERY2);
        $statement2->execute();
        $nb2 = $statement2->fetch();

        //nb3
        $RAW_QUERY3 = 'SELECT  COUNT(*) as nb3  from commande where (date_emission >= \'2020-03-01\') AND (date_emission <= \'2020-03-31\');';
        $statement3 = $em->getConnection()->prepare($RAW_QUERY3);
        $statement3->execute();
        $nb3 = $statement3->fetch();

        //nb4
        $RAW_QUERY4 = 'SELECT  COUNT(*) as nb4  from commande where (date_emission >= \'2020-04-01\') AND (date_emission <= \'2020-04-31\');';
        $statement4 = $em->getConnection()->prepare($RAW_QUERY4);
        $statement4->execute();
        $nb4 = $statement4->fetch();

        //nb5
        $RAW_QUERY5 = 'SELECT  COUNT(*) as nb5  from commande where (date_emission >= \'2020-05-01\') AND (date_emission <= \'2020-05-31\');';
        $statement5 = $em->getConnection()->prepare($RAW_QUERY5);
        $statement5->execute();
        $nb5 = $statement5->fetch();

        //nb6
        $RAW_QUERY6 = 'SELECT  COUNT(*) as nb6  from commande where (date_emission >= \'2020-06-01\') AND (date_emission <= \'2020-06-31\');';
        $statement6 = $em->getConnection()->prepare($RAW_QUERY6);
        $statement6->execute();
        $nb6 = $statement6->fetch();

        //nb7
        $RAW_QUERY7 = 'SELECT  COUNT(*) as nb7  from commande where (date_emission >= \'2020-07-01\') AND (date_emission <= \'2020-07-31\');';
        $statement7= $em->getConnection()->prepare($RAW_QUERY7);
        $statement7->execute();
        $nb7 = $statement7->fetch();

        //nb8
        $RAW_QUERY8 = 'SELECT  COUNT(*) as nb8  from commande where (date_emission >= \'2020-08-01\') AND (date_emission <= \'2020-08-31\');';
        $statement8= $em->getConnection()->prepare($RAW_QUERY8);
        $statement8->execute();
        $nb8 = $statement8->fetch();

        //nb9
        $RAW_QUERY9 = 'SELECT  COUNT(*) as nb9  from commande where (date_emission >= \'2020-09-01\') AND (date_emission <= \'2020-09-31\');';
        $statement9= $em->getConnection()->prepare($RAW_QUERY9);
        $statement9->execute();
        $nb9 = $statement8->fetch();

        //nb10
        $RAW_QUERY10 = 'SELECT  COUNT(*) as nb10  from commande where (date_emission >= \'2020-10-01\') AND (date_emission <= \'2020-10-31\');';
        $statement10= $em->getConnection()->prepare($RAW_QUERY10);
        $statement10->execute();
        $nb10 = $statement10->fetch();

        //nb11
        $RAW_QUERY11 = 'SELECT  COUNT(*) as nb11  from commande where (date_emission >= \'2020-11-01\') AND (date_emission <= \'2020-11-31\');';
        $statement11= $em->getConnection()->prepare($RAW_QUERY11);
        $statement11->execute();
        $nb11 = $statement11->fetch();

        //nb12
        $RAW_QUERY12 = 'SELECT  COUNT(*) as nb12  from commande where (date_emission >= \'2020-12-01\') AND (date_emission <= \'2020-12-31\');';
        $statement12= $em->getConnection()->prepare($RAW_QUERY12);
        $statement12->execute();
        $nb12 = $statement12->fetch();

        $jan=intval($nb1['nb1']);
        $fev=intval($nb2['nb2']);
        $mars=intval($nb3['nb3']);
        $avril=intval($nb4['nb4']);
        $mai=intval($nb5['nb5']);
        $juin=intval($nb6['nb6']);
        $jui=intval($nb7['nb7']);
        $aout=intval($nb8['nb8']);
        $sep=intval($nb9['nb9']);
        $oct=intval($nb10['nb10']);
        $nov=intval($nb11['nb11']);
        $dec=intval($nb12['nb12']);


        $tab=array();
        $tab[1]=$jan;
        $tab[2]=$fev;
        $tab[3]=$mars;
        $tab[4]=$avril;
        $tab[5]=$mai;
        $tab[6]=$juin;
        $tab[7]=$jui;
        $tab[8]=$aout;
        $tab[9]=$sep;
        $tab[10]=$oct;
        $tab[11]=$nov;
        $tab[12]=$dec;

        return $this->render('@Produit/DashboardAdmin/Commande/stat_commande.html.twig',array('tab'=> $tab));

    }

}