<?php

namespace MessageBundle\Controller;

use MessageBundle\Entity\categ;
use MessageBundle\Entity\categstock;
use MessageBundle\Entity\reponse;
use MessageBundle\Entity\stock;
use MessageBundle\Form\categType;
use MessageBundle\Form\categstockType;
use MessageBundle\Form\stockType;

use UserBundle\Entity\User;
use Symfony\Component\HttpFoundation\Request;
use MessageBundle\Entity\message;
use MessageBundle\Form\messageType;
use MessageBundle\Form\reponseType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\User\UserInterface;
use Doctrine\ORM\Query;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Dompdf\Dompdf;
use Dompdf\Options;

use Doctrine\ORM\QueryBuilder;


class MessageController extends Controller
{

    public function generate_pdfsAction(Request $request){
        //$id = $request->get('id');

        $em = $this->getDoctrine()->getManager();
        $tache = $em->getRepository('MessageBundle:message');
        $options = new Options();
        $options->set('defaultFont', 'Roboto');
        $dompdf = new Dompdf($options);
        $data = array(
            'headline' => 'my headline'
        );
        $html = $this->renderView('@Message/message/addMessage.html.twig', array(
            'tache' => $tache
        ));
        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'portrait');
        $dompdf->render();
        $dompdf->stream("testspdf.pdf", [
            "Attachment" => true
        ]);
    }



    public function addmessageAction(Request $request)
    {

        $message = new Message();
        $form= $this->createForm(messageType::class, $message);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $message->setCreateur($this->getUser());
            $message->setPostdate(new \DateTime('now'));

            $em->persist($message);
            $em->flush();
            $message = \Swift_Message::newInstance()
                ->setSubject('Vous avez une nouvelle tache')
                ->setFrom(array('mohamedchtioui1996@gmail.com' => 'Easy Ride'))
                ->setTo("mohamed.chtioui@esprit.tn")
                ->setBody("<h1>Bonjour ,<br/> Agent Chtioui Mohamed : </h1><br><p>Votre Reclamation a ete bien Recue  </p>", 'text/html');
            $this->get('mailer')->send($message);

            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('addMessage');
        }

        return $this->render('@Message/message/addMessage.html.twig', array(
            "form"=> $form->createView()
        ));
    }






    public function addStockAction(Request $request)
    {

        $message = new stock();
        $form= $this->createForm(stockType::class, $message);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $message->setCreateur($this->getUser());
            $message->setPostdate(new \DateTime('now'));

            $em->persist($message);
            $em->flush();
            $message = \Swift_Message::newInstance()
                ->setSubject('Vous avez une nouvelle tache')
                ->setFrom(array('mohamedchtioui1996@gmail.com' => 'Easy Ride'))
                ->setTo("mohamed.chtioui@esprit.tn")
                ->setBody("<h1>Bonjour ,<br/> Agent Chtioui Mohamed : </h1><br><p>Votre Reclamation a ete bien Recue  </p>", 'text/html');
            $this->get('mailer')->send($message);

            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('addStock');
        }

        return $this->render('@Message/message/addStock.html.twig', array(
            "form"=> $form->createView()
        ));
    }













    public function listmessagesAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();

        $dql="SELECT bp from MessageBundle:message bp  " ;
        $query = $em->createQuery($dql);
       // $id=$this->getUser()->getId();
        //$query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur LIKE : id')->setParameter('id', '%"'.$id.'"%');
      //  $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur : = user')->setParameter('user', '%"'.$user->getId().'"%');
       // $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur=2');

        $paginator= $this->get ('knp_paginator');
        dump(get_class($paginator));
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );
        return $this->render('@Message/message/showMsg.html.twig', array(
            "res" => $result,
        ));

    }


    public function listmessagesupAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();

        $dql="SELECT bp from MessageBundle:message bp ORDER BY bp.postdate ASC " ;
        $query = $em->createQuery($dql);
        // $id=$this->getUser()->getId();
        //$query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur LIKE : id')->setParameter('id', '%"'.$id.'"%');
        //  $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur : = user')->setParameter('user', '%"'.$user->getId().'"%');
        // $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur=2');

        $paginator= $this->get ('knp_paginator');
        dump(get_class($paginator));
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );
        return $this->render('@Message/message/showMsg.html.twig', array(
            "res" => $result,
        ));

    }



    public function listmessagesdownAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();

        $dql="SELECT bp from MessageBundle:message bp ORDER BY bp.postdate DESC " ;
        $query = $em->createQuery($dql);
        // $id=$this->getUser()->getId();
        //$query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur LIKE : id')->setParameter('id', '%"'.$id.'"%');
        //  $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur : = user')->setParameter('user', '%"'.$user->getId().'"%');
        // $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur=2');

        $paginator= $this->get ('knp_paginator');
        dump(get_class($paginator));
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );
        return $this->render('@Message/message/showMsg.html.twig', array(
            "res" => $result,
        ));

    }




    public function listmessagesStockAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();

        $dql="SELECT bp from MessageBundle:stock bp " ;
        $query = $em->createQuery($dql);
        // $id=$this->getUser()->getId();
        //$query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur LIKE : id')->setParameter('id', '%"'.$id.'"%');
        //  $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur : = user')->setParameter('user', '%"'.$user->getId().'"%');
        // $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur=2');

        $paginator= $this->get ('knp_paginator');
        dump(get_class($paginator));
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );
        return $this->render('@Message/message/showMsgStock.html.twig', array(
            "res" => $result,
        ));

    }



    public function listmessagesuserAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();

        //$dql="SELECT bp from MessageBundle:message bp WHERE bp.createur = : id " ;
        //$query = $em->createQuery($dql);
        // $id=$this->getUser()->getId();
        //$query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur LIKE : id')->setParameter('id', '%"'.$id.'"%');
        //  $query = $em->createQuery('SELECT bp from MessageBundle:message bp WHERE bp.createur : = user')->setParameter('user', '%"'.$user->getId().'"%');
       // $message=new message()->setCreateur($this->getUser());
        //$message->setCreateur($this->getUser());
         $id=2;

        $query = $em->createQuery('SELECT bp from MessageBundle:message bp  WHERE bp.createur = 2   ');
//        $query->setParameter('id', $id);

//SELECT a, u FROM Advert a JOIN a.user u WHERE u.age = 25
        $paginator= $this->get ('knp_paginator');
        dump(get_class($paginator));
        $result = $paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );
        return $this->render('@Message/message/showMsgUser.html.twig', array(
            "res" => $result,
        ));

    }




    public function updatemessageAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('MessageBundle:message')->find($id);
        $form=$this->createForm(messageType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $p->setPostdate(new \DateTime('now'));
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
            if ($this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
                return $this->redirectToRoute('listerMessage');
            }

        }
        return $this->render('@Message/message/update.html.twig', array(
            "form"=> $form->createView()
        ));
    }


    public function updatemessageStockAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('MessageBundle:stock')->find($id);
        $form=$this->createForm(stockType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $p->setPostdate(new \DateTime('now'));
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
            if ($this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
                return $this->redirectToRoute('listmessagesStock');
            }

        }
        return $this->render('@Message/message/updateStock..twig', array(
            "form"=> $form->createView()
        ));
    }









    public function deletemessageAction(Request $request)
    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $message = $em->getRepository('MessageBundle:message')->find($id);
        $em->remove($message);
        $em->flush();
        if ($this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {

            return $this->redirectToRoute('listerMessage');
        }
        elseif(!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {

            return $this->redirectToRoute('listmessagesuser');
        }

    }


    public function deletemessageStockAction(Request $request)
    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $message = $em->getRepository('MessageBundle:stock')->find($id);
        $em->remove($message);
        $em->flush();
        if ($this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {

            return $this->redirectToRoute('listmessagesStock');
        }
        elseif(!$this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {

            return $this->redirectToRoute('listmessagesuser');
        }

    }






    public function addCatAction(Request $request)
    {
        $car = new categ();
        $form = $this->createForm(categType::class, $car);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('showCateg');
        }


        return $this->render('@Message/message/addCateg.html.twig',array(
            'Form'=> $form->createView()));
    }





    public function addCatStockAction(Request $request)
    {
        $car = new categstock();
        $form = $this->createForm(categstockType::class, $car);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('showCategStock');
        }


        return $this->render('@Message/message/addCategStock.html.twig',array(
            'Form'=> $form->createView()));
    }









    public function manCatAction(){


        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('MessageBundle:categ')->findAll();
        return $this->render('@Message/message/showCateg.html.twig',array(
            'car'=> $car));
    }



    public function manCatStockAction(){


        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('MessageBundle:categstock')->findAll();
        return $this->render('@Message/message/showCategStock.html.twig',array(
            'car'=> $car));
    }


    public function editCatAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('MessageBundle:categ')->find($id);
        $form=$this->createForm(categType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
            return $this->redirectToRoute('showCateg');

        }
        return $this->render('@Message/message/editCateg.html.twig', array(
            "form"=> $form->createView()
        ));
    }



    public function editCatStockAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('MessageBundle:categstock')->find($id);
        $form=$this->createForm(categstockType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
            return $this->redirectToRoute('showCategStock');

        }
        return $this->render('@Message/message/editCategStock.html.twig', array(
            "form"=> $form->createView()
        ));
    }












    public function deleteCatAction(Request $request, $qdt)
    {


        $em = $this->getDoctrine()->getManager();

        $carP = $em->getRepository('MessageBundle:message')->find($qdt);
        $car = $em->getRepository('MessageBundle:categ')->find($qdt);

        if (!$carP) {
            $em->remove($car);
            $em->flush();
        }


        return $this->redirectToRoute('showCateg');
    }


    public function deleteCatStockAction(Request $request, $qdt)
    {


        $em = $this->getDoctrine()->getManager();

        $carP = $em->getRepository('MessageBundle:message')->find($qdt);
        $car = $em->getRepository('MessageBundle:categstock')->find($qdt);

        if (!$carP) {
            $em->remove($car);
            $em->flush();
        }


        return $this->redirectToRoute('showCategStock');
    }




    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $posts =  $em->getRepository('MessageBundle:message')->findEntitiesByString($requestString);
        if(!$posts) {
            $result['posts']['error'] = "Message not found :( ";
        } else {
            $result['posts'] = $this->getRealEntities($posts);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($posts){
        foreach ($posts as $posts){
            $realEntities[$posts->getId()] = [$posts->getPhoto(),$posts->getTitle()];

        }
        return $realEntities;
    }

    public function addRepAction(Request $request)
    {

        $message = new reponse();
        $form= $this->createForm(reponseType::class, $message);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $message->setCreateur($this->getUser());
            $message->setPostdate(new \DateTime('now'));
            $mh=$message->getDescription();
            //$me=$message->getCreateur();
            $em->persist($message);

            $this->getDoctrine()->getManager()->flush();


            $em->flush();


            $message1 = \Swift_Message::newInstance()
                ->setSubject('Votre Reclamation a ete bien traite ')
                ->setFrom(array('Tunfugees@gmail.com' => 'Tunfugees'))
                ->setTo("tunfegees@gmail.com")
                ->setBody("<h1>Bonjour    :  </h1><br><p><h3> $mh</h3> </p>", 'text/html');
            $this->get('mailer')->send($message1);


            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('listerMessage');
        }

        return $this->render('@Message/message/addRep.html.twig', array(
            "form"=> $form->createView()
        ));
    }

    public function mapAction()
    {

        return $this->render('@Message/message/newMap.html.twig');
    }
    public function videoPromAction()
    {

        return $this->render('@Message/message/promovid.html.twig');
    }


    }
/*
public function mailAction($name, \Swift_Mailer $mailer)
{
    $message = (new \Swift_Message('Votre Reclamation a a ete bien traiter '))
        ->setFrom('send@example.com')
        ->setTo('mohamed.chtioui@esprit.tn')
        ->setBody(
            $this->renderView(
                '@Message/message/registration.html.twig',
                ['name' => $name]
            ),
            'text/html'
        )

    ;

    $mailer->send($message);

    // or, you can also fetch the mailer service this way
    // $this->get('mailer')->send($message);

    //return $this->render(...);
    return $this->render('@Message/message/promovid.html.twig');

}
*/
