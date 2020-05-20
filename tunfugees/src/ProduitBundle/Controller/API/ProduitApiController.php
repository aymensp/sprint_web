<?php

namespace ProduitBundle\Controller\API;

use ProduitBundle\Entity\Produits;
use ProduitBundle\Entity\Categorie;

use UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 *
 * @Route("annonceApi")
 */
class ProduitApiController extends Controller
{
    /**
     * @Route("/", name="getAll_annonce")
     */
    public function getall()
    {
        $task = $this->getDoctrine()->getManager()
            ->getRepository(Produits::class)
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($task);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/categories") , name="Categorie_api_all")
     */

    public function getAllCategorieAction()
    {
        $cat = $this->getDoctrine()->getManager()->getRepository(Categorie::class)->findAll();


        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($cat);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/annonce/{id}") , name="find_api_annonce")
     */
    public function getAnnonceById($id)
    {
        $ann = $this->getDoctrine()->getManager()->getRepository(Produits::class)->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($ann);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/annonce/{text}", name="annonceBytext")
     */
    public function getAnnonceAction($text)
    {
        $ann = $this->getDoctrine()->getManager()
            ->getRepository(Produits::class)
            ->RechercheTitreProduit($text);
        //dump($ann);exit();
        $serializer = $this->get('jms_serializer');

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($ann);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/delete/{id}", name="produit_api_supp")
     * @Method({"GET", "POST"})
     */
    public function deleteAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $ann = $em
            ->getRepository('ProduitBundle:Produits')
            ->find($id);


        $em->remove($ann);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($ann);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/modifier", name="modifier")
     */
    public function jsoneditAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $annonce = $em->getRepository('ProduitBundle:Produits')->find($request->get('idProd'));


        $annonce->setNomprod($request->get('nomProd'));
        $annonce->setDescription($request->get('description'));
        $annonce->setPrix($request->get('prix'));
        $annonce->setDispo('dispo');
        $annonce->setNomref($request->get('nomRef'));
        $annonce->setLikes(0);
        $annonce->setViews(0);
        $annonce->setNote(0);
        $annonce->setImg($request->get('img'));
        $categorie = $em->getRepository(Categorie::class)->find($request->get('categorie'));

        $annonce->setCategorie($categorie);

        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($annonce);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/new", name="annoce_api_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $annonce = new Produits();
        $annonce->setNomprod($request->get('nomProd'));
        $annonce->setDescription($request->get('description'));
        $annonce->setPrix($request->get('prix'));
        $annonce->setDispo('dispo');
        $annonce->setNomref($request->get('nomRef'));
        $annonce->setLikes(0);
        $annonce->setViews(0);
        $annonce->setNote(0);
        $annonce->setImg($request->get('img'));
        $categorie = $em->getRepository(Categorie::class)->find($request->get('categorie'));

        $annonce->setCategorie($categorie);


        $em->persist($annonce);
        $em->flush();

            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($annonce);
            return new JsonResponse($formatted);
    }

    /**
     * @Route("/categorie/{cat}", name="json_cat_annopnce")
     */
    public function RecherchCategorieAction($cat)
    {

        $em = $this->getDoctrine()->getManager();
        $annnonce = new Produits();
        $annnonce = $em->getRepository('ProduitBundle:Produits')->findBycategorie($cat);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($annnonce);
        return new JsonResponse($formatted);
    }






    /**
     * Deletes a annonce entity.
     *
     * @Route("/delete/{id}", name="json_dellet_annonce")
     * @Method({"GET", "DELETE"})
     */
    public function deleteAnnonceAction($id)
    {
        $annonce = $this->getDoctrine()->getRepository('ProduitBundle:Produits')->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($annonce);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($annonce);
        return new JsonResponse($formatted);
    }

    /**
     * Finds and displays a user entity.
     *
     * @Route("/viwes/{id}", name="f_json_viwe")
     * @Method({"GET", "POST"})
     */
    public function setViweAction($id)
    {
        $annonce = $this->getDoctrine()->getRepository('ProduitBundle:Produits')->find($id);
        $annonce->setViews($annonce->getViews()+1);
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($annonce);
        return new JsonResponse($formatted);
    }

    /**
     * Creates a new Categorie et annonce entity.
     *
     * @Route("/likes/{id}", name="json_like_annonce")
     * @Method({"GET", "POST"})
     */
    public function newJaimeAction($id)
    {
            $em = $this->getDoctrine()->getManager();
            $annonce = $em->getRepository('ProduitBundle:Produits')->find($id);
            $annonce->setLikes($annonce->getLikes() + 1);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($annonce);
            return new JsonResponse($formatted);
    }
    /**
     * Creates a new Categorie et annonce entity.
     *
     * @Route("/notes/{id}/{note}", name="json_note_annonce")
     * @Method({"GET", "POST"})
     */
    public function updateNoteAction($id,$note)
    {
        $em = $this->getDoctrine()->getManager();
        $annonce = $em->getRepository('ProduitBundle:Produits')->find($id);
        if($annonce->getNote() ==0)
        {
            $annonce->setNote(($annonce->getNote()+$note));
        }else
        {
            $annonce->setNote(($annonce->getNote()+$note)/2);
        }
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($annonce);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/trier/{val}", name="trier_json")
     *
     */
    public function TrierAction(Request $request)
    {

        $val = $request->get('val');
        //dump($val);exit();
        if ($val == 'PR') {
            $em = $this->getDoctrine()->getManager();

            $annonces = $em->getRepository('ProduitBundle:Produits')->trierPlusRecent();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($annonces);
            return new JsonResponse($formatted);
        } elseif ($val == 'PE') {
            $em = $this->getDoctrine()->getManager();
            $annonces = $em->getRepository('ProduitBundle:Produits')->trierPrixElv();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($annonces);
            return new JsonResponse($formatted);
        } elseif ($val == 'PB') {

            $em = $this->getDoctrine()->getManager();
            $annonces = $em->getRepository('ProduitBundle:Produits')->trierPrixBas();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($annonces);
            return new JsonResponse($formatted);
        }
    }

    /**
     *
     * @Route("/recherche", name="json_recher")
     * @Method({"GET", "POST"})
     */
    public function rechercheAction(Request $request)
    {
        $keyWord = $request->get('keyWord');
        // dump($keyWord);

            $annonce = $this->getDoctrine()->getRepository('ProduitBundle:Produits')->RechercheTitreProduit($keyWord);
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($annonce);
            return new JsonResponse($formatted);


    }



}
