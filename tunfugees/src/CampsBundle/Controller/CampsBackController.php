<?php

namespace CampsBundle\Controller;

use CampsBundle\Entity\Camps;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 *
 * @Route("Ca")
 */
class CampsBackController extends Controller
{
    /**
     * @Route("/camps", name="Ca_camps_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        $em = $this->getDoctrine()->getManager();
        $camps = $em->getRepository('CampsBundle:Camps')->findAll();
        return $this->render('@Camps/indexBack.html.twig', array(
            'camps' => $camps,
        ));
    }
    /**
     * Deletes a Camps entity.
     *
     * @Route("/camps/delete/{idcamp}", name="Ca_camps_delete")
     * @Method({"GET", "DELETE"})
     */
    public function deleteAction($idcamp)
    {

        $camps = $this->getDoctrine()->getRepository('CampsBundle:Camps')->find($idcamp);
        $em = $this->getDoctrine()->getManager();
        $em->remove($camps);
        $em->flush();

        return $this->redirectToRoute('Ca_camps_index');
    }
    /**
     * Creates a new Camps entity.
     *
     * @Route("/camps/new", name="Ca_camps_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $camps = new Camps();


        $formcamps = $this->createForm('CampsBundle\Form\CampsType',$camps);

        $formcamps->handleRequest($request);
        if($formcamps->isSubmitted() && $formcamps->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist( $camps );
            $em->flush();
            return $this->redirectToRoute('Ca_camps_index');
        }
        return $this->render('@Camps/newCamp.html.twig', array(
            'formcamp' => $formcamps->createView(),
        ));

    }

    /**
     * Displays a form to edit an existing rep entity.
     *
     * @Route("/camps/{idcamp}/edit", name="Ca_camp_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Camps $camps)
    {
        if (!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            throw new AccessDeniedException("Vous n'êtes pas autorisés à accéder à cette page!", Response::HTTP_FORBIDDEN);
        }
        $deleteForm = $this->createDeleteForm($camps);
        $editForm = $this->createForm('CampsBundle\Form\CampsType', $camps);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('Ca_camp_edit', array('idcamp' => $camps->getIdcamp()));
        }

        return $this->render('@Camps/editcamp.html.twig', array(
            'user' => $camps,
            'form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
    /**
     * Creates a form to delete a user entity.
     *
     * @param Camps $camps The Camps Entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Camps $camps)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('Ca_camps_delete', array('idcamp' => $camps->getIdcamp())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }
    /**
     * @Route("/afficher", name="camps_api_index")
     * @Method("GET")
     */
    public function indexApiAction()
    {
        $pubs = $this->getDoctrine()->getManager()
            ->getRepository(Camps::class)
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($pubs);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/back/delete/{id}", name="camps_api_delete")
     * @Method("GET")
     */
    public function jsondeleteAction($id)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository(Camps::class)
            ->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($publication);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($publication);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/new/{nbremax}/{nomCamp}/{adresse}"), name="camp_api_new")
     * @Method({"GET", "POST"})
     */
    public function jsonaddAction(Request $request, int $nbremax,String $nomCamp , String $adresse )
    {
        $events= new Camps();
        $em = $this->getDoctrine()->getManager();

        $events->setNbrmax($nbremax);
        $events->setCategories($nomCamp);
        $events->setAdresse($adresse);
        $em->persist($events);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    /**
     * Finds and displays a user entity.
     *
     * @Route("/viwes/{id}/{nbremax}/{nomCamp}/{adresse}", name="camp_json_viwe")
     * @Method({"GET", "POST"})
     */

    public function jsoneditAction(int $id,int $nbremax,String $nomCamp , String $adresse)
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('CampsBundle:Camps')->find($id);
        $events->setIdcamp($id);
        $events->setNbrmax($nbremax);
        $events->setCategories($nomCamp);
        $events->setAdresse($adresse);



        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }

}
