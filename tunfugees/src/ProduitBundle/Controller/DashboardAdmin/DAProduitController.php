<?php

namespace ProduitBundle\Controller;

namespace ProduitBundle\Controller\DashboardAdmin;

use ProduitBundle\Entity\Produits;
use ProduitBundle\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Finder\Exception\AccessDeniedException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Ob\HighchartsBundle\Highcharts\Highchart;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 *
 * @Route("da")
 */
class DAProduitController extends Controller
{
    /**
     * @Route("/annonce", name="da_annonce_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        $em = $this->getDoctrine()->getManager();
        $categorieAnnonce = $em->getRepository('ProduitBundle:Categorie')->findAll();
        $annonce = $em->getRepository('ProduitBundle:Produits')->findAll();
        return $this->render('@Produit/DashboardAdmin/Produit/index.html.twig', array(
            'annonce' => $annonce,
            'categorieAnnonce' => $categorieAnnonce,
        ));
    }
    /**
     * @Route("/allanonces", name="da_json")
     * @Method("GET")
     */
    public function allAction()
    {
        $task = $this->getDoctrine()->getManager()->getRepository(Produits::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($task);
        return new  JsonResponse($formated);
    }

    /**
     * Creates a new Categorie et annonce entity.
     *
     * @Route("/annonce/new", name="da_annonce_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }

        $annonce = new Produits();
        $categorieAnnonce = new Categorie();
        $formCategorie = $this->createForm('ProduitBundle\Form\CategorieType', $categorieAnnonce);
        $formAnnonce = $this->createForm('ProduitBundle\Form\ProduitsType',$annonce);
        $formCategorie->handleRequest($request);
        $formAnnonce->handleRequest($request);
        if($formAnnonce->isSubmitted() && $formAnnonce->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $annonce->setViews(0);
            $annonce->setLikes(0);
            $annonce->setDispo("Disponible");

            $em->persist($annonce);
            $em->flush();
            return $this->redirectToRoute('da_annonce_index');
        }
        if ($formCategorie->isSubmitted() && $formCategorie->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($categorieAnnonce);
            $em->flush();
            return $this->redirectToRoute('da_annonce_index');
        }
        return $this->render('@Produit/DashboardAdmin/Produit/new.html.twig', array(
            'formCatAnn' => $formCategorie->createView(),
            'formAnnonce' => $formAnnonce->createView()
        ));

    }
    /**
     * Displays a form to edit an existing categorie entity.
     *
     * @Route("/annonce/{id}/edit/categorie", name="da_annonce_cat_edit")
     * @Method({"GET", "POST"})
     */
    public function editCategorieAction(Request $request, Categorie $categorieAnnonce)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        $editFormcat = $this->createForm('ProduitBundle\Form\CategorieType', $categorieAnnonce);
        $editFormcat->handleRequest($request);
        if ($editFormcat->isSubmitted() && $editFormcat->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('da_annonce_index');
        }
        return $this->render('@Produit/DashboardAdmin/Produit/editCat.html.twig', array(
            'Categories' => $categorieAnnonce,
            'formcat'    => $editFormcat->createView(),
        ));

    }
    /**
     * Displays a form to edit an existing Annonce entity.
     *
     * @Route("/annonce/{id}/editAnnonce", name="da_annonce_edit")
     * @Method({"GET", "POST"})
     */
    public function editAnnonceAction(Request $request, Produits $annonce)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }


        $editFormAnn = $this->createForm('ProduitBundle\Form\ProduitsType',$annonce);
        $editFormAnn->handleRequest($request);
        if ($editFormAnn->isSubmitted() && $editFormAnn->isValid())
        {

            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('da_annonce_index');
        }

        return $this->render('@Produit/DashboardAdmin/Produit/editProduit.html.twig', array(
            'annonce'    => $annonce,
            'formAnn'    => $editFormAnn->createView(),
        ));

    }
    /**
     * Deletes a annonce entity.
     *
     * @Route("/annonce/delete/{id}", name="da_annonce_delete")
     * @Method({"GET", "DELETE"})
     */
    public function deleteAnnonceAction($id)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        $annonce = $this->getDoctrine()->getRepository('ProduitBundle:Produits')->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($annonce);
        $em->flush();

        return $this->redirectToRoute('da_annonce_index');
    }
    /**
     * Finds and displays a user entity.
     *
     * @Route("/annonce/{id}", name="da_annonce_show")
     * @Method("GET")
     */
    public function showAction(Produits $annonce)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        return $this->render('@Produit/DashboardAdmin/Produit/show.html.twig', array(
            'annonce' => $annonce,
        ));
    }
    /**
     * Deletes a categorie entity.
     *
     * @Route("/annonce/categorie/delete/{id}", name="da_annonce_cat_delete")
     * @Method({"GET", "DELETE"})
     */
    public function deleteCategorieAction($id)
    {

        $categorieAnnonce = $this->getDoctrine()->getRepository('ProduitBundle:Categorie')->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($categorieAnnonce);
        $em->flush();

        return $this->redirectToRoute('da_annonce_index');
    }
    /**
     * Displays a form to edit an existing Annonce entity.
     *
     * @Route("/statiAdmin", name="da_annonce_stat")
     * @Method({"GET", "POST"})
     */
    public function statAction()
    {


        $em = $this->getDoctrine()->getManager();
        //nb1
        $RAW_QUERY = 'SELECT  COUNT(*) as nb1 from produits where (date_creation >= \'2019-01-01\') AND (date_creation <= \'2019-01-31\');';
        $statement = $em->getConnection()->prepare($RAW_QUERY);
        $statement->execute();
        $nb1 = $statement->fetch();
        //nb2
        $RAW_QUERY2 = 'SELECT  COUNT(*) as nb2  from produits where (date_creation >= \'2019-02-01\') AND (date_creation <= \'2019-02-31\');';
        $statement2 = $em->getConnection()->prepare($RAW_QUERY2);
        $statement2->execute();
        $nb2 = $statement2->fetch();

        //nb3
        $RAW_QUERY3 = 'SELECT  COUNT(*) as nb3  from produits where (date_creation >= \'2019-03-01\') AND (date_creation <= \'2019-03-31\');';
        $statement3 = $em->getConnection()->prepare($RAW_QUERY3);
        $statement3->execute();
        $nb3 = $statement2->fetch();

        //nb4
        $RAW_QUERY4 = 'SELECT  COUNT(*) as nb4  from produits where (date_creation >= \'2019-04-01\') AND (date_creation <= \'2019-04-31\');';
        $statement4 = $em->getConnection()->prepare($RAW_QUERY4);
        $statement4->execute();
        $nb4 = $statement4->fetch();

        //nb5
        $RAW_QUERY5 = 'SELECT  COUNT(*) as nb5  from produits where (date_creation >= \'2019-05-01\') AND (date_creation <= \'2019-05-31\');';
        $statement5 = $em->getConnection()->prepare($RAW_QUERY5);
        $statement5->execute();
        $nb5 = $statement5->fetch();

        //nb6
        $RAW_QUERY6 = 'SELECT  COUNT(*) as nb6  from produits where (date_creation >= \'2019-06-01\') AND (date_creation <= \'2019-06-31\');';
        $statement6 = $em->getConnection()->prepare($RAW_QUERY6);
        $statement6->execute();
        $nb6 = $statement6->fetch();

        //nb7
        $RAW_QUERY7 = 'SELECT  COUNT(*) as nb7  from produits where (date_creation >= \'2019-07-01\') AND (date_creation <= \'2019-07-31\');';
        $statement7= $em->getConnection()->prepare($RAW_QUERY7);
        $statement7->execute();
        $nb7 = $statement7->fetch();

        //nb8
        $RAW_QUERY8 = 'SELECT  COUNT(*) as nb8  from produits where (date_creation >= \'2019-08-01\') AND (date_creation <= \'2019-08-31\');';
        $statement8= $em->getConnection()->prepare($RAW_QUERY8);
        $statement8->execute();
        $nb8 = $statement8->fetch();

        //nb9
        $RAW_QUERY9 = 'SELECT  COUNT(*) as nb9  from produits where (date_creation >= \'2019-09-01\') AND (date_creation <= \'2019-09-31\');';
        $statement9= $em->getConnection()->prepare($RAW_QUERY9);
        $statement9->execute();
        $nb9 = $statement8->fetch();

        //nb10
        $RAW_QUERY10 = 'SELECT  COUNT(*) as nb10  from produits where (date_creation >= \'2019-10-01\') AND (date_creation <= \'2019-10-31\');';
        $statement10= $em->getConnection()->prepare($RAW_QUERY10);
        $statement10->execute();
        $nb10 = $statement10->fetch();

        //nb11
        $RAW_QUERY11 = 'SELECT  COUNT(*) as nb11  from produits where (date_creation >= \'2019-11-01\') AND (date_creation <= \'2019-11-31\');';
        $statement11= $em->getConnection()->prepare($RAW_QUERY11);
        $statement11->execute();
        $nb11 = $statement11->fetch();

        //nb12
        $RAW_QUERY12 = 'SELECT  COUNT(*) as nb12  from produits where (date_creation >= \'2019-12-01\') AND (date_creation <= \'2019-12-31\');';
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

        return $this->render('@Produit/DashboardAdmin/Produit/stat_produit.html.twig',array('tab'=> $tab));

    }
}
