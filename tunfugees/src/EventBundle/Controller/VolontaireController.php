<?php

namespace EventBundle\Controller;


use EventBundle\Entity\Event;
use EventBundle\Entity\Volontaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class VolontaireController extends Controller
{
    public function ajoutvoloAction()
    {
        return $this->render('@Event/Volontaire/ajoutvolo.html.twig');
    }
    public function showAction()
    {
        $volontaires= $this->getDoctrine()
            ->getRepository(Volontaire::class)->findAll();
        return $this->render('@Event/DashboardAdmin/Volontaire/show.html.twig',array('volontaires'=>$volontaires));
    }
    public function deleteVoloAction($id)
    {
        $m=$this->getDoctrine()->getManager();
        $evenement = $m->getRepository(Volontaire::class)->find($id);
        $m->remove($evenement);
        $m->flush();

        return$this->redirectToRoute('show');
    }
    public function editVoloAction(Request $request,Volontaire $evenement)
    {


        $editForm = $this->createForm('EventBundle\Form\VolontaireType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('edit', array('id' => $evenement->getIdVol()));
        }

        return $this->render('@Event/DashboardAdmin/Volontaire/editVolo.html.twig', array(
            'evenement' => $evenement,
            'form' => $editForm->createView(),
        ));
    }
    public function ajouterVoloAction(Request $request)
    {

        $categorieEvts = new Volontaire();
        $formCateg = $this->createForm('EventBundle\Form\VolontaireType', $categorieEvts);
        $formCateg->handleRequest($request);

        if ($formCateg->isSubmitted() && $formCateg->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($categorieEvts);
            $em->flush();
            return $this->redirectToRoute('ajouterVolo');
        }

        return $this->render('@Event/Volontaire/ajoutvolo.html.twig', array(
            'formCateg' => $formCateg->createView(),
        ));
    }
    public function participerAjaxAction(Request $request)
    {
        $id        = $request->get('id');
        $em        = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository('EcoBundle:Evenement')->find($id);
        $user      = $this->get('security.token_storage')->getToken()->getUser();
//        $user->addEventsParticipes($evenement);
        $evenement->addPartcipants($user);
        $em->persist($evenement);
        $em->persist($user);
        $em->flush();

        $events = $em->getRepository('EcoBundle:Evenement')->findAll();
        $template = $this->render(
            '@Eco/Front/Evenement/allEvents.html.twig',
            [
                'evenements' => $events,
            ]
        )->getContent()
        ;

        $json     = json_encode($template);
        $response = new Response($json, 200);
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }



}
